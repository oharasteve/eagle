@setlocal

@set STARTDIR=%~dp0

@set CONTACTS=C:\Users\O'Hara\AppData\Local\Google\Picasa2\contacts\contacts.xml

@java -Xmx512M -classpath %STARTDIR%ThumbNailer PicasaReader %STARTDIR%..\photos %STARTDIR%faces.csv "%CONTACTS%"
