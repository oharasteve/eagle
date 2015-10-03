@rem changes all filenames under here so they don't have any parens in them.

@setlocal

@set STARTDIR=%~dp0

@if "%~1" == "" echo Directory is required && goto :eof
@pushd "%~1"

@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l
@set BAT=%TEMP%\noparens_%TIMEVAR%.bat

@echo. > "%BAT%"
@for /r %%i in ( "*(*)*" ) do @echo "%%i" | awk -f "%STARTDIR%noparens.awk" >> "%BAT%"
@call "%BAT%"

@if exist "%BAT%" del /f "%BAT%"

@popd
