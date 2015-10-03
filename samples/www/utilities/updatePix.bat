@setlocal

@set URL=http://www.oharasteven.com/photos/screensaver.php
@set OUT=C:\Program Files (x86)\Cool Captions\coolcaptions.ini

@java -classpath ThumbNailer ReadHtml "%URL%" > "%OUT%"
@echo Updated "%OUT%"
