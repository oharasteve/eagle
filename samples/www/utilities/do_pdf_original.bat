@setlocal

@if "%1" == "" echo Need a directory under originals && goto :eof

@set U=%~dp0
@set IN=%~1
@set BIG=bigthumbs

@if not exist "%IN%" echo Directory %IN% does not exist && goto :eof
@if not exist "%IN%\%BIG%" echo Creating %IN%\%BIG% && mkdir "%IN%\%BIG%"
@if not exist "%IN%\%BIG%" echo Unable to create %IN%\%BIG% && goto :eof

@rem Make sure no spaces in file names
@call "%U%nospaces.bat" "%IN%"

@rem Set up Ghostscript
@set RESOLUTION=-r80
@set SIZE=1000
@set GSDIR=C:\Program Files (x86)\gs\gs9.00\bin
@set GSCMD1=save pop currentglobal true setglobal false/product where{pop product(Ghostscript)search{pop pop pop revision %SIZE%
@set GSCMD2=ge{pop true}if}{pop}ifelse}if{/pdfdict where{pop pdfdict begin/pdfshowpage_setpage[pdfdict/pdfshowpage_setpage
@set GSCMD3=get{dup type/nametype eq{dup/BIGputFile eq{pop/AntiRotationHack}{dup/MediaBox eq revision 650 ge and{/THB.CropHack{1
@set GSCMD4=index/CropBox pget{2 index exch/MediaBox exch put}if}def/THB.CropHack cvx}if}ifelse}if}forall]cvx def end}if}if setglobal
@set GSCMD=%GSCMD1% %GSCMD2% %GSCMD3% %GSCMD4%

@rem Make sure all the pdf's have a thumbnail, and a picture of the first page
@for /r "%IN%" %%i in ( *.pdf ) do @(
	@call :doBig %%i
	@call "%U%\gen_pdf_thumb.bat" %%i "thumbs" 100 -r20
)
@goto :eof

:doBig
	@set PDFIN=%~1
	@set JPGOUT=%~d1%~p1%BIG%\%~n1_pdf.jpg
	@if exist "%JPGOUT%" goto :eof

	@"%GSDIR%\gswin32.exe" -q -dTextAlphaBits=4 -dGraphicsAlphaBits=4 -dQUIET -dNOPAUSE -dBATCH -sDEVICE=jpeg %RESOLUTION% -sOutputFile="%JPGOUT%" -c "%GSCMD%" -f "%PDFIN%"
	@dir "%JPGOUT%" | find /i "jpg"
  @goto :eof
