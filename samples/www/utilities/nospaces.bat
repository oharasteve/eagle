@rem changes all filenames under here so they don't have any spaces in them.

@setlocal

@set STARTDIR=%~dp0

@if "%~1" == "" echo Directory is required && goto :eof
@pushd "%~1"

@for /f "tokens=1,2,3,4 delims=:. " %%i in ('echo %TIME%') do @set TIMEVAR=%%i%%j%%k%%l
@set BAT=%TEMP%\nospaces_%TIMEVAR%.bat

@rem do directories first
@echo. > "%BAT%"
@for /r /d %%i in ( "* *" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"

@rem now do files
@for /r %%i in ( "*'*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*,*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*!*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*&*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*+*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "* *" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*(*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@for /r %%i in ( "*)*" ) do @echo "%%i" | awk -f "%STARTDIR%nospaces.awk" >> "%BAT%"
@call "%BAT%"

@if exist "%BAT%" del /f "%BAT%"

@popd
