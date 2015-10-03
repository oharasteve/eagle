@setlocal

@if "%~1" == "" echo Need a directory && goto :eof

@set STARTDIR=%~dp0

echo Processing %1 ...
call "%STARTDIR%nospaces.bat" "%~1"
call "%STARTDIR%thumb.bat" "%~1"
call "%STARTDIR%genhtml.bat" "%~1" > "%~1\index.html"

call "%STARTDIR%count_originals.bat"
