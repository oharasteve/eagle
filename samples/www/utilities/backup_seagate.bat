@setlocal

@for /f "tokens=2,3,4 delims=/ " %%i in ('date /t') do @set DATEVAR=%%k%%i%%j
@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l

@set DRV=I:
@if not exist %DRV%\GoFlex.ico echo. && echo DRIVE LETTER IS WRONG && goto :eof
@set OUT=%DRV%\backups\backup_%DATEVAR%_%TIMEVAR%.txt

@echo Starting backup on %DATE% at %TIME%
@echo Starting backup on %DATE% at %TIME% > "%OUT%"

xcopy "c:\users\daisy" "%DRV%\users\daisy" /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

xcopy "c:\SVN" "%DRV%\SVN" /EXCLUDE:skip.backup /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

xcopy "c:\SVN2" "%DRV%\SVN2" /EXCLUDE:skip.backup /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

xcopy "c:\users\steve" "%DRV%\users\steve f" /EXCLUDE:skip.backup /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

xcopy "c:\www" "%DRV%\www" /EXCLUDE:skip.backup /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

xcopy "c:\www2" "%DRV%\www2" /EXCLUDE:skip.backup /f /e /v /r /h /i /d /y >> "%OUT%"
@tail -1 "%OUT%"

@echo Finished backup on %DATE% at %TIME% >> "%OUT%"
@echo Finished backup on %DATE% at %TIME%

@echo Log written to %OUT%
