@if "%~1" == "" echo Need a directory && goto :eof

call reduce.bat "%~1"
if exist "%~1\reduced" copy /y "%~1\reduced\*" "%~1"
if exist "%~1\reduced" rmdir /s /q "%~1\reduced"

call nospaces.bat "%~1"
call thumb.bat "%~1"
call picinfo.bat "%~1" > photos.csv
