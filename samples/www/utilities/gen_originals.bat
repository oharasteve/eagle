@setlocal

@if "%~1" == "" echo Need a directory && goto :eof

@set U=utilities
echo Processing %1 ...
call %U%\nospaces "%~1"
call %U%\thumb "%~1"
call genhtml.bat "%~1" > "%~1\index.html"
