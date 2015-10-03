@setlocal

@set STARTDIR=%~dp0

@if "%1" == "" echo Need a directory && goto :eof

@call %STARTDIR%reduce200.bat "%1"
@if exist "%1\thumbs\reduced" copy /y "%1\thumbs\reduced\*" "%1\thumbs"
@if exist "%1\thumbs\reduced" rmdir /s /q "%1\thumbs\reduced"
