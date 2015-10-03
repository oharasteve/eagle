@setlocal

@if "%~3" == "" echo Need a dir and two suffixes && goto :eof

@if not exist "%~1\*%~2" echo Can't find "%~1\*%~2" && goto :eof

@for /r "%~1" %%i in ( *%~2 ) do @call :do1 "%%i" "%~3"
@goto :eof

:do1
@echo move "%~dpnx1" "%~dpn1%~2"
@move "%~dpnx1" "%~dpn1%~2"
@goto :eof