@setlocal

@set STARTDIR=%~dp0

@if "%~1" == "" echo Need input.pdf && goto :eof
@set PDFIN=%~1

@rem validate input filename
@set INSUFFIX=%~x1
@if not "%INSUFFIX%" == ".PDF" @(
    @if not "%INSUFFIX%" == ".pdf" @(
	    echo Input file must end in pdf, not %INSUFFIX% && goto :eof
    )
)

@rem create output thumbs directory, if needed
@set OUTDIR=%~d1%~p1images
@if not exist "%OUTDIR%" @(
    @echo Creating Directory %OUTDIR%
    @mkdir "%OUTDIR%"
)

@rem Create output jpg filename
@set OUT=%OUTDIR%\%~n1_pdf_image.bmp
@if exist "%OUT%" goto :eof
@set DEVICE=bmp16m

@rem Set up Ghostscript
@set GSDIR=C:\Program Files (x86)\gs\gs9.00\bin
@set GSCMD1=save pop currentglobal true setglobal false/product where{pop product(Ghostscript)search{pop pop pop revision 600
@set GSCMD2=ge{pop true}if}{pop}ifelse}if{/pdfdict where{pop pdfdict begin/pdfshowpage_setpage[pdfdict/pdfshowpage_setpage
@set GSCMD3=get{dup type/nametype eq{dup/OutputFile eq{pop/AntiRotationHack}{dup/MediaBox eq revision 650 ge and{/THB.CropHack{1
@set GSCMD4=index/CropBox pget{2 index exch/MediaBox exch put}if}def/THB.CropHack cvx}if}ifelse}if}forall]cvx def end}if}if setglobal
@set GSCMD=%GSCMD1% %GSCMD2% %GSCMD3% %GSCMD4%

@rem Create it!
@"%GSDIR%\gswin32.exe" -q -dTextAlphaBits=4 -dGraphicsAlphaBits=4 -dQUIET -dNOPAUSE -dBATCH -sDEVICE=%DEVICE% -r1200 -sOutputFile="%OUT%" -c "%GSCMD%" -f "%PDFIN%"
@dir "%OUT%" | find /i "jpg"
