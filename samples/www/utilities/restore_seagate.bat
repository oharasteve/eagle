@setlocal

@for /f "tokens=2,3,4 delims=/ " %%i in ('date /t') do @set DATEVAR=%%k%%i%%j
@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l

@set DRV=D:
@if not exist %DRV%\GoFlex.ico echo Wrong drive && goto :eof
@set OUT=%DRV%\backups\restore_%DATEVAR%_%TIMEVAR%.txt

@echo Starting restore on %DATE% at %TIME%
@echo Starting restore on %DATE% at %TIME% > "%OUT%"

xcopy "%DRV%\users\daisy" "C:\users\daisy" /f /e /v /r /h /d /y >> "%OUT%"

@for %%i in ( collages family originals photos tree ) do (
    xcopy "%DRV%\www2\%%i" "C:\www2\%%i" /EXCLUDE:skip.restore /f /e /v /r /h /d /y >> "%OUT%"
)

@echo Finished restore on %DATE% at %TIME% >> "%OUT%"
@echo Finished restore on %DATE% at %TIME%

@echo Log written to %OUT%
