@setlocal

@set STARTDIR=%~dp0
@set JPGS=/utilities

@if "%~3" == "" echo Usage: Indent Dir ShortDir && goto :eof

@rem Dang DOS treats the "less than" and "greater than" symbols specially
@set SED=sed "s+{+<+g;s+}+>+g"

@rem Ignore all the 'thumbs' directories
@if "%~n2" == "thumbs" goto :eof

@set TOGGLEMODE=%1
@set DOXDIR=%~2
@set PARENT=%~3
@set OUT=%DOXDIR%\index.html
@echo %TIME% Processing %~1. %~n2

@rem Copy it, just in case it is unchanged ...
@set SAVE=%OUT%_DELETEME
@set TMP=%TEMP%\del_indx.out
@if exist "%SAVE%" del /f "%SAVE%"
@if exist "%OUT%" copy /y "%OUT%" "%SAVE%" > "%TMP%" 2>&1

@echo {html} | %SED% > "%OUT%"
@echo {head} | %SED% >> "%OUT%"
@echo {style type="text/css"} | %SED% >> "%OUT%"
@echo    table { display:inline-table; page-break-inside:avoid; } >> "%OUT%"
@echo    td { padding:5; } >> "%OUT%"
@echo {/style} | %SED% >> "%OUT%"
@echo {title}Directory %PARENT%{/title} | %SED% >> "%OUT%"
@echo {/head} | %SED% >> "%OUT%"
@echo {body} | %SED% >> "%OUT%"
@echo {center} | %SED% >> "%OUT%"
@echo {h1}Directory %PARENT%{/h1} | %SED% >> "%OUT%"

@rem Process all the directories
@for /d %%i in ( "%DOXDIR%\*" ) do @(
	@call :DirSub "%%i" "%~1"
)

@rem Keep subroutines inline
@goto EndDirSub
:DirSub
	@set DIRNAME=%~n1
	
    @rem skip thumbs and pdfwords subdirs
    @if "%DIRNAME%" == "thumbs" goto :eof
    @if "%DIRNAME%" == "pdfwords" goto :eof
	
	@echo   {table}{tr}{td}{center}{a href="%DIRNAME%/index.html"} | %SED% >> "%OUT%"
	@echo     {img src="%JPGS%/folder.jpg"}{br}{font size=-1}%DIRNAME%{/font}{/a} | %SED% >> "%OUT%"
	@echo   {/center}{/td}{/tr}{/table} | %SED% >> "%OUT%"
	
	@rem Recursive call in DOS, wow
	@call "%STARTDIR%gen_index_html.bat" ". %~2" "%~1" "%Parent%/%DIRNAME%"
	@goto :eof
:EndDirSub

@rem Process all the files
@for %%i in ( "%DOXDIR%\*" ) do @(
	@call :FileSub "%%i"
)

@rem Keep subroutine inline
@goto EndFileSub
:FileSub
	@set NAME=%~nx1
	@set SUFFIX=%~x1
	@set FULL=%~dpnx1
	
    @rem Skip index.html and other junk
    @if "%NAME%" == "index.html" goto :eof
    @if "%NAME%" == ".htaccess" goto :eof
    @if "%NAME%" == "pdf_count.txt" goto :eof
    @if "%NAME%" == "sizes.bat" goto :eof
    @if "%NAME%" == "sizes.txt" goto :eof
	@if "%~1" == "%SAVE%" goto :eof
	
	@echo   {table}{tr}{td}{center}{a href="%NAME%"} | %SED% >> "%OUT%"
	@if "%SUFFIX%" == ".jpg" @(
	    @echo     {img src="thumbs/%~n1_thumb.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
	) else @(
        @if "%SUFFIX%" == ".JPG" @(
			@echo     {img src="thumbs/%~n1_thumb.JPG"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
		) else @(
			@if "%SUFFIX%" == ".pdf" @(
				@echo     {img src="thumbs/%~n1_pdf_thumb.jpg"}{br}{font size=-1}%NAME%{br}| %SED% >> "%OUT%"
                @awk -f "%STARTDIR%count_pdf_pages.awk" "%FULL%" | %SED% >> "%OUT%"
			) else @(
				@if "%SUFFIX%" == ".xls" @(
					@echo     {img src="%JPGS%/excel.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
				) else @(
					@if "%SUFFIX%" == ".xlsx" @(
						@echo     {img src="%JPGS%/excel.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
					) else @(
						@if "%SUFFIX%" == ".doc" @(
							@echo     {img src="%JPGS%/word.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
						) else @(
							@if "%SUFFIX%" == ".docx" @(
								@echo     {img src="%JPGS%/word.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
							) else @(
								@echo     {img src="%JPGS%/question.jpg"}{br}{font size=-1}%NAME%{/font}{/a} | %SED% >> "%OUT%"
							)
						)
					)
				)
			)
		)
	)
	@echo   {/center}{/td}{/tr}{/table} | %SED% >> "%OUT%"

	@goto :eof
:EndFileSub

@rem Finish the index.html file
@echo {/center} | %SED% >> "%OUT%"
@echo {/body} | %SED% >> "%OUT%"
@echo {/html} | %SED% >> "%OUT%"

@if exist "%SAVE%" @(
	@diff "%OUT%" "%SAVE%" > "%TMP%" 2>&1 
	@call :EmptyCheck "%OUT%" "%SAVE%" "%TMP%"
	@del /f "%SAVE%"
) else (
	@echo            Created "%OUT%"
)

@goto :EndEmptyCheck

:EmptyCheck
	@if "%~z3" == "0" @(
		@copy /y "%~2" "%~1" > "%TMP%" 2>&1
		@rem echo Identical files ... restoring date/time of original
	) else (
		@echo            Updated "%~1"
	)
	@goto :eof
:EndEmptyCheck
