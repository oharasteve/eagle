@setlocal
@set OUT=%TEMP%\count.out
@echo. > %OUT%
@for /r %1 %%i in ( *.htm* ) do @echo %%i >> %OUT% && @awk -f count.awk "%%i" >> %OUT%
@awk -f count2.awk "%OUT%"