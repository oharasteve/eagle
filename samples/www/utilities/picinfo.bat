@setlocal

@set STARTDIR=%~dp0

@if not defined WWW set WWW=C:\www2

@java -Xmx512M -classpath %STARTDIR%\ThumbNailer PicInfo "%WWW%" %*