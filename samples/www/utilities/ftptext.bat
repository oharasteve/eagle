@setlocal

@if "%3" == "" echo Usage: password directory file && goto :eof
@set PWD=%1
@set DIR1=%2
@set FILE=%3

@for /f "tokens=2,3,4 delims=/ " %%i in ('date /t') do @set DATEVAR=%%i%%j%%k
@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l

@set FTP=%TEMP%\temp_%TIMEVAR%.ftp
@set TTT=%TEMP%\temp_%TIMEVAR%.bat

@echo @set DIR2=%DIR1% | sed "s+\\+/+g;s+\.\.++" > %TTT%
@call %TTT%
@if exist %TTT% del /f %TTT%

@if not exist %DIR1%\%FILE% echo No such file %DIR1%\%FILE% && goto :eof
@dir %DIR1%\%FILE%

@echo open ftp.oharasteve.com> %FTP%
@echo steve@oharasteve.com>> %FTP%
@echo %PWD%>> %FTP%
@echo lcd %DIR1%>> %FTP%
@echo mkdir %DIR2%>> %FTP%
@echo cd %DIR2%>> %FTP%
@echo asc>> %FTP%
@echo put %FILE%>> %FTP%
@echo quit>> %FTP%

@echo Starting on %DATE% at %TIME%
ftp -s:%FTP%
@echo Finished at %TIME%

@if exist %FTP% del /f %FTP%
