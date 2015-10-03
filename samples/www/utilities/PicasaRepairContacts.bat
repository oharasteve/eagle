@setlocal

@if not defined WWW set WWW=C:\www2

@set PHOTOS=%WWW%\photos

@pushd "%PHOTOS%"
@attrib -H /S %PHOTOS%\.picasa.ini
@popd

@java -classpath ThumbNailer PicasaContacts2 "%PHOTOS%"

@pushd "%PHOTOS%"
@attrib +H /S %PHOTOS%\.picasa.ini
@popd
