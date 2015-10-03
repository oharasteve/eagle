@setlocal

@set STARTDIR=%~dp0
@for /l %%i in (1,1,25) do @echo.

@echo This takes a little over four hours, as of Sep 26, 2015

@echo ===== Backing up DB locally
@call "%STARTDIR%backupDB.bat"

@echo ===== Updating faces.csv from Picasa
@call "%STARTDIR%PicasaReader.bat"

@echo ===== Updating all picture captions for screen saver
@call "%STARTDIR%PicCaptions.bat"

@echo ===== Updating all Dox, etc
@call "%STARTDIR%update_Dox.bat"

@echo ===== Making sure all photos are sized correctly in DB
@call "%STARTDIR%CheckPhotoSizes.bat"
