@setlocal

@set OUT=c:\temp\check_tree.out

@java -Xmx512M -classpath ThumbNailer ReadHtml "http://www.oharasteven.com/utilities/check_tree.php" > "%OUT%"
@find "has a bad" "%OUT%"
