@setlocal

@set STARTDIR=%~dp0

@dir /b "%~1" | awk -f %STARTDIR%genhtml.awk -v "DIR=%~n1"