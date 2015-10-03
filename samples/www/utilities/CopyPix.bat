@echo Obsolete && goto :eof

@if not defined WWW set WWW=C:\www2

@set FROMDIR=%WWW%
@set FROMDIR1=%FROMDIR%\photos
@set TODIR=C:\Pix
@set TODIR1=%TODIR%\photos

@echo Starting at %TIME%

@if exist "%TODIR%" echo Removing %TODIR% && rmdir /s /q "%TODIR%"
@if not exist "%TODIR1%" mkdir "%TODIR1%"

@echo _thumb > %TEMP%\thumb.txt

@echo Copy pictures from %FROMDIR1% to %TODIR1%
@xcopy /s /q /y "%FROMDIR1%\*.jpg" "%TODIR1%" /EXCLUDE:%TEMP%\thumb.txt

@for /r "%TODIR%" %%i in ( thumbs ) do @rmdir /q "%%i" > %TEMP%\del.me 2>&1

@echo Finished at %TIME%

@echo Update "C:\Program Files (x86)\Cool Captions\coolcaptions.ini" from screensaver.xml
