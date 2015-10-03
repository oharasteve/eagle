@setlocal

@set STARTDIR=%~dp0

@if "%~1" == "" echo Need input.pdf directory size resolution && goto :eof
@set PDFIN=%~1

@set THUMBS=thumbs
@if not "%~2" == "" @set THUMBS=%~2

@set SIZE=300
@if not "%~3" == "" @set SIZE=%~3

@set RESOLUTION=-r40
@if not "%~4" == "" @set RESOLUTION=%~4

@set PDFWORDS=pdfwords
@if not "%~5" == "" @set PDFWORDS=%~5

@rem validate input filename
@set INSUFFIX=%~x1
@if not "%INSUFFIX%" == ".PDF" @(
    @if not "%INSUFFIX%" == ".pdf" @(
	    echo Input file must end in pdf, not %INSUFFIX% && goto :eof
    )
)

@rem create output thumbs directory, if needed
@set OUTDIR=%~d1%~p1%THUMBS%
@if not exist "%OUTDIR%" @(
    @echo Creating Directory %OUTDIR%
    @mkdir "%OUTDIR%"
)

@rem Create output jpg filename
@set JPGOUT=%OUTDIR%\%~n1_pdf_thumb.jpg
@if exist "%JPGOUT%" goto :JPGDONE

@rem Set up Ghostscript
@set GSDIR=C:\Program Files (x86)\gs\gs9.00\bin
@set GSCMD1=save pop currentglobal true setglobal false/product where{pop product(Ghostscript)search{pop pop pop revision 600
@set GSCMD2=ge{pop true}if}{pop}ifelse}if{/pdfdict where{pop pdfdict begin/pdfshowpage_setpage[pdfdict/pdfshowpage_setpage
@set GSCMD3=get{dup type/nametype eq{dup/OutputFile eq{pop/AntiRotationHack}{dup/MediaBox eq revision 650 ge and{/THB.CropHack{1
@set GSCMD4=index/CropBox pget{2 index exch/MediaBox exch put}if}def/THB.CropHack cvx}if}ifelse}if}forall]cvx def end}if}if setglobal
@set GSCMD=%GSCMD1% %GSCMD2% %GSCMD3% %GSCMD4%

@rem Create it!
@"%GSDIR%\gswin32.exe" -q -dTextAlphaBits=4 -dGraphicsAlphaBits=4 -dQUIET -dNOPAUSE -dBATCH -sDEVICE=jpeg %RESOLUTION% -sOutputFile="%JPGOUT%" -c "%GSCMD%" -f "%PDFIN%"
@call "%STARTDIR%reduce_pdf.bat" "%JPGOUT%" "%SIZE%"
@dir "%JPGOUT%" | find /i "jpg"

:JPGDONE

@set PDFBOX=C:\Program Files (x86)\PDFBox\pdfbox-app-1.7.1.jar

@rem create output pdfwords directory, if needed
@set WORDSDIR=%~d1%~p1%PDFWORDS%
@if not exist "%WORDSDIR%" @(
    @echo Creating Directory %WORDSDIR%
    @mkdir "%WORDSDIR%"
)

@set WORDSOUT=%WORDSDIR%\%~n1_pdf.txt
@if exist "%WORDSOUT%" goto :WORDSDONE

@echo %PDFIN% > "%WORDSOUT%"
@java -jar "%PDFBOX%" ExtractText -console "%PDFIN%" >> "%WORDSOUT%" 2>&1
@wc -l "%WORDSOUT%"

:WORDSDONE
