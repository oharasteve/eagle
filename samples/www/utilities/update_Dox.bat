@setlocal

@echo Starting at %TIME% ... should take about 50 minutes total

@set U=%~dp0
@set D=..\Dox

@if "%PWD2%" == "" echo Please set PWD2 && goto :eof

@rem Make sure no parentheses in file names
call "%U%noparens.bat" "%D%"

@rem Make sure no spaces in file names
call "%U%nospaces.bat" "%D%"

@rem Make sure all the jpg's have a thumbnail
call "%U%thumb.bat" -size 300 "%D%"

@rem Make sure all the pdf's have a thumbnail, and a picture of the first page
@for /r "%D%" %%i in ( *.pdf ) do @(
    @rem call "%U%gen_pdf_thumb.bat" "%%i" "thumbs" 100 -r20
    @call "%U%gen_pdf_thumb.bat" "%%i" "thumbs" 300 -r40
    @rem call "%U%gen_pdf_thumb.bat" "%%i" "bigthumbs" 600 -r200
)

@rem Remove extra junk directories
@for /d /r "%D%" %%i in ( reduced* ) do @rmdir "%%i"

@echo.
@echo Expect about 30 minutes for this part to complete ...
@echo.

@rem Create a bunch of index.html files
@java -classpath "ThumbNailer" GenIndexHtml "%D%"

@rem create a database backup
@call "%U%backupDB.bat"

@rem update photo count in index.html
@call "%U%updatePhotoCount.bat"

@rem update original counts
@call "%U%count_originals.bat"

@rem update detailed statistics
@call "%U%update_statistics.bat"

@set BAT=C:\temp\sync3.bat
@if exist "%BAT%" del /f "%BAT%"
@call "%U%sync2.bat"
@if not exist "%BAT%" goto :eof

@rem Do the ftp automatically
@call "%BAT%"

@rem Update the cool captions screen saver
@call "%U%updatePix.bat"

@rem check family tree connections
@call "%U%check_tree.bat"

@rem check around for orphans
@call "%U%crawl.bat"

@rem Check for files not properly named
@dir /s /b "%D%\doc*.pdf" > %TEMP%\delme 2>&1
@if not errorlevel 1 type %TEMP%\delme
