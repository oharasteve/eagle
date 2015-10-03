@setlocal

@set STARTDIR=%~dp0
@set CP=C:\Program Files\Java\EXIF
@set CP1=%CP%\metadata-extractor-2.6.4.jar
@set CP2=%CP%\xmpcore.jar
@"java" -Xmx512M -classpath "%STARTDIR%ThumbNailer;%CP1%;%CP2%" ImageDetails %*
