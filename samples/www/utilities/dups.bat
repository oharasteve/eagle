@pushd ..\photos
@dir /s *.jpg | grep -v _thumb | grep -i jpg | awk '{print $5}' | cygsort -f > %TEMP%\jpgdups1.out
@type %TEMP%\jpgdups1.out | awk -f ..\utilities\dups1.awk | cygsort -f -u > %TEMP%\jpgdups2.out
@echo. > %TEMP%\jpgdups3.out
@for /f %%i in ( %TEMP%\jpgdups2.out ) do @echo BREAK >> %TEMP%\jpgdups3.out && dir /s %%i >> %TEMP%\jpgdups3.out
@type %TEMP%\jpgdups3.out | awk -f ..\utilities\dups2.awk > ..\utilities\dups.out
@type ..\utilities\dups.out
@popd
