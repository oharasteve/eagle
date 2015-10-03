@setlocal

@echo.
@echo Note ... this takes a couple minutes to run

@if not defined WWW set WWW=C:\www2

@set OUT=C:\temp\sync3.remote
@set CNT=0

:again
@set /a CNT=CNT+1
@if exist "%OUT%" del /f "%OUT%"

@for %%i in ( justPhotos justDox noPhotosDox ) do @(
	@echo Reading %%i at %TIME%
	@java -Xmx512M -classpath ThumbNailer ReadHtml "http://www.oharasteven.com/utilities/sync3.php?code=%%i" >> "%OUT%"
	@if errorlevel 1 @(
		@tail -10 "%OUT%"
		@echo ReadHtml failed ... trying again
		@goto again
	)
	@wc -l C:\temp\sync3.remote

	@rem GoDaddy spews out nonsense on a failure sometimes
	@find /i "!DOCTYPE HTML" "%OUT%" > %TEMP%\delme.out 2>&1
	@if not errorlevel 1 @(
		@tail -10 "%OUT%"
		@echo GoDaddy failed ... trying again, times=%CNT%
    @echo.
		@goto again
	)
)

@echo Reading local files at %TIME%
@dir /s .. > C:\temp\sync3.local
@dir /a:h /s .. >> C:\temp\sync3.local
@wc -l C:\temp\sync3.local

@echo Comparing files at %TIME%
@java -Xmx512M -classpath ThumbNailer Sync %WWW% C:\temp\sync3.remote C:\temp\sync3.local > C:\temp\sync3.bat
@if errorlevel 1 @(
	@echo.
	@echo Last few processed ...
	@tail -5 C:\temp\sync3.remote
	@echo Quitting %0 ... Sync failed
	@goto :eof
)

@grep "call :do1" C:\temp\sync3.bat | wc -l | awk '{ print "Files=" $1 }'
@echo n C:\temp\sync3.bat
@echo.

@echo Finished at %TIME%
