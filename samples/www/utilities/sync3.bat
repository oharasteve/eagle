@echo WRONG ONE -- USE sync2.bat please!
@call sync2.bat
@goto :eof



@echo.
@echo Note ... this takes a couple minutes to run

@echo Reading remote files at %TIME%
@java -Xmx512M -classpath ThumbNailer ReadHtml "http://www.oharasteven.com/utilities/sync3.php" > C:\temp\sync3.remote
@if errorlevel 1 @(
	@tail -10 C:\temp\sync3.remote
	@echo Quitting %0 ... ReadHtml failed
	@goto :eof
)
@wc -l C:\temp\sync3.remote

@rem GoDaddy spews out nonsense on a failure sometimes
@find /i "!DOCTYPE HTML" C:\temp\sync3.remote > %TEMP%\delme.out 2>&1
@if not errorlevel 1 @(
	@tail -10 C:\temp\sync3.remote
	@echo Quitting %0 ... GoDaddy failed
	@goto :eof
)

@echo Reading local files at %TIME%
@dir /s .. > C:\temp\sync3.local
@wc -l C:\temp\sync3.local

@echo Comparing files at %TIME%
@java -Xmx512M -classpath ThumbNailer Sync 2 C:\temp\sync3.remote C:\temp\sync3.local > C:\temp\sync3.bat
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
