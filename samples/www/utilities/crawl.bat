@setlocal

@set STARTDIR=%~dp0

@if not defined WWW set WWW=C:\www2

@set OUT=C:\temp\crawl.out
@set CSV=C:\temp\links.csv

@rem Let awk put commas in numbers!
@set LC_ALL=en_US.UTF-8

@echo Starting at %TIME%

@echo Reading Photo database at %TIME%
@java -Xmx512M -classpath ThumbNailer ReadHtml "http://www.oharasteven.com/utilities/findLinks.php" > "%CSV%"
@if errorlevel 1 echo ReadHtml failed && goto :eof
@wc -l "%CSV%"

@echo Crawling through files at %TIME%
@pushd ..
@java -Xmx512M -classpath "%STARTDIR%ThumbNailer" Crawler "%WWW%" index.html \temp\sync3.local "%CSV%" "%OUT%" > c:\temp\crawl2.out
@if errorlevel 1 echo Crawler failed && goto :eof

@awk '/Links checked / { print $4 }' "c:\temp\crawl2.out" | awk -f ".\statistics\commas.awk" > "%TEMP%\links_count.out"
@type c:\temp\crawl2.out
@awk -f utilities\crawl.awk "%OUT%"
@popd
@wc -l "%OUT%"

@echo Finished at %TIME%
