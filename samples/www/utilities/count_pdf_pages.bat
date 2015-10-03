@setlocal

@if "%1" == "" echo need a directory && goto :eof

@set OUT=%TEMP%\count_pdf_pages.out

@echo. > "%OUT%"
@for /r "%~1" %%i in ( index*.html ) do @grep "pages=" %%i | sed "s+<i>pages=+pg +;s+</i></font></a>++" >> "%OUT%"
@awk '/pg/ { pdf += 1; cnt += $2; } END { print "PDF Docs = ", pdf, " Pages = ", cnt; }' < "%OUT%"

