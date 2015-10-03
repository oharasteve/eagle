@setlocal

@set STARTDIR=%~dp0

@echo Starting at %TIME%
@echo This takes almost 3 hours (as of Sep 9, 2015)

@if not defined WWW set WWW=C:\www2

@set INP=C:\Program Files (x86)\Cool Captions\coolcaptions.ini
@set OUTDIR=%WWW%\Pix
@set PHOTOS=%WWW%\photos

@if not exist "%OUTDIR%" @(
  @echo Creating directory "%OUTDIR%"
  @mkdir "%OUTDIR%"
)

@java -classpath "%STARTDIR%ThumbNailer" PicCaptions "%INP%" "%OUTDIR%" "%PHOTOS%"

@pushd %OUTDIR%
@type "%STARTDIR%PicCaptions1.html" > index.html
@for /d %%i in ( * ) do @(
  @call %STARTDIR%genPicCaptions.bat %OUTDIR%\%%i > %OUTDIR%\%%i\index.html
  @echo LliJLa href="%%i/index.html"J%%iL/aJ| sed "s+L+<+g;s+J+>+g" >> index.html
)
@type "%STARTDIR%PicCaptions2.html" >> index.html
@popd

@echo Finished at %TIME%
