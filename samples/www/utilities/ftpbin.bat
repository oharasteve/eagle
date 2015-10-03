@if not defined CNTOF @goto nocnt
@set /a CNT=CNT+1

@if %CNTOF% == 0 @(
	@echo ************* FTP Count = %CNT%
)
@if NOT %CNTOF% == 0 @(
	@echo ************* FTP Count = %CNT% of %CNTOF%
)

:nocnt

@if "%1" == "-2" @(
    @set HOST=ftp.oharasteven.com
    @set USER=oharasteve
    @set OUT=c:\temp\www2
    @shift
) else (
	@set HOST=ftp.oharasteve.com
	@set USER=steve@oharasteve.com
	@set OUT=c:\temp\www
)

@if "%3" == "" echo Usage: [-2] password directory file && goto :eof
@set PWD=%1
@set DIR1=%2
@set FILE=%3

:redo

@for /f "tokens=2,3,4 delims=/ " %%i in ('date /t') do @set DATEVAR=%%i%%j%%k
@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l

@if not defined TEMPNAME set TEMPNAME=temp_
@set FTP=%OUT%\%TEMPNAME%%TIMEVAR%.ftp
@set TMP=%OUT%\%TEMPNAME%%TIMEVAR%.tmp
@set TTT=%OUT%\%TEMPNAME%%TIMEVAR%.bat

@if exist "%TTT%" goto redo
@if exist "%FTP%" goto redo
@echo @set DIR2=%DIR1%| sed "s+\\\\+/+g;s+\.\.++" > "%TTT%"
@call "%TTT%"
@if exist "%TTT%" del /f "%TTT%"

@if not exist "%DIR1%\%FILE%" echo No such file %DIR1%\%FILE% && goto :eof
@ls -l "%DIR1%\%FILE%"

@echo Starting on %DATE% at %TIME%

@if defined DIR2 (
	@echo open %HOST%> "%FTP%"
	@echo %USER%>> "%FTP%"
	@echo %PWD%>> "%FTP%"
    @echo mkdir %DIR2%>> "%FTP%"
	@echo quit>> "%FTP%"

	@ftp -v -s:"%FTP%"
	@find "No such file or directory" "%TMP%" > %TEMP%\delme2.out 2>&1
	@if not errorlevel 1 @(
		@echo.
		@echo Unable to create directory %DIR2%
		@goto :SKIP
	)
)

@echo open %HOST%> "%FTP%"
@echo %USER%>> "%FTP%"
@echo %PWD%>> "%FTP%"
@echo lcd %DIR1%>> "%FTP%"
@if defined DIR2 (
    @echo cd %DIR2%>> "%FTP%"
)
@echo bin>> "%FTP%"
@echo put %FILE%>> "%FTP%"
@echo dir %FILE%>> "%FTP%"
@echo quit>> "%FTP%"

ftp -v -s:"%FTP%"
@echo Finished at %TIME%

:SKIP

@if exist "%FTP%" del /f "%FTP%"
@if exist "%TMP%" del /f "%TMP%"

@sleep 1
@echo.
