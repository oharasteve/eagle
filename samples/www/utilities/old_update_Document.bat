@setlocal

@set STARTDIR=%~dp0

@if "%1" == "" echo Need a directory && goto :eof

@set D=%~1
@set U=..\..\www2\utilities

@rem Make sure no spaces in file names
call "%U%\nospaces.bat" "%D%"

@rem Make sure all the jpg's have a thumbnail
call "%U%\thumb.bat" "%D%"

@rem Make sure all the pdf's have a thumbnail
@for /r "%D%" %%i in ( *.pdf ) do @(
    @call "%U%\gen_pdf_thumb.bat" "%%i"
)

@rem Remove extra junk directories
@for /d /r "%D%" %%i in ( reduced* ) do @rmdir "%%i"

@rem Create a bunch of index.html files
@call "%U%\gen_index_html.bat" "" "%D%" "%D%" ".."

@rem Make sure everything worked
@for /r "%D%" %%i in ( index*.html ) do @(
	@find "</html>" %%i > %TEMP%\delme.out 2>&1
	@if errorlevel 1 echo Incomplete %%i
)

@call "%U%\count_pdf_pages.bat" "%D%"
