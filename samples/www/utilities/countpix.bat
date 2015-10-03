@dir /s ..\photos\*.jpg > %TEMP%\delme.out
@dir /s ..\photos\*_thumb.jpg > %TEMP%\delme2.out
@pushd %TEMP%
@tail -2 delme.out
@tail -2 delme2.out
@popd