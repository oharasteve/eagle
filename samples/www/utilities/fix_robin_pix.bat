@setlocal

@set STARTDIR=%~dp0

@if "%~1" == "" echo Need a long prefix && goto :eof
@set PREFIX=%~1

@pushd \Users\Steve\Scans
@for /l %%i in (1,1,9) do @(
  move image%%i.jpeg %PREFIX%0%%i.jpg > %TEMP%\del.me 2>&1
  if not errorlevel 1 echo move image%%i.jpeg %PREFIX%0%%i.jpg
)
@for /l %%i in (10,1,99) do @(
  move image%%i.jpeg %PREFIX%%%i.jpg > %TEMP%\del.me 2>&1
  if not errorlevel 1 echo move image%%i.jpeg %PREFIX%%%i.jpg
)
@popd