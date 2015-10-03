@setlocal

@set STARTDIR=%~dp0

@"java" -Xmx512M -classpath %STARTDIR%ThumbNailer ThumbNailer %*