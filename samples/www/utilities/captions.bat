@rem find all existing photos and their captions

@pushd ..\photos
@for /r %%i in ( "*.htm*" ) do @awk -f ..\utilities\captions.awk "%%i"
@popd
