@setlocal

@set OUT=..\family.sql

@echo Starting at %TIME%

@java -Xmx512M -classpath ThumbNailer ReadHtml "http://www.oharasteven.com/utilities/backupDB.php" > "%OUT%"
@if errorlevel 1 echo ReadHtml failed && goto :eof
@wc -l "%OUT%"

@echo Finished at %TIME%
