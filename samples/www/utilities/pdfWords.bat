@setlocal

@if "%~2" == "" echo Usage: pdfWords in.pdf out.txt

@set IN=%~1
@SET OUT=%~2

@set PDFBOX=C:\Program Files (x86)\PDFBox\pdfbox-app-1.7.1.jar

java -jar "%PDFBOX%" ExtractText -console "%IN%" > "%OUT%"
