@if exist findcaps.csv del /f findcaps.csv
@if "%1" == "" echo Usage findcaps ..\photos && goto :eof
@for /r %1 %%i in ( *.htm* ) do @awk -f findcaps.awk "%%i" >> findcaps.csv
@echo Check findcaps.csv