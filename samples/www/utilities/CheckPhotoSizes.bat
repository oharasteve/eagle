@setlocal

@set STARTDIR=%~dp0

@echo Starting at %TIME%
@echo This takes about 45 minutes (as of Sep 2015)

@if not defined WWW set WWW=C:\www2

@set SQL=%WWW%\family.sql
@set OUT=C:\temp\CheckPhotoSizes.sql

@java -classpath "%STARTDIR%ThumbNailer" CheckPhotoSizes "%SQL%" "%WWW%" > "%OUT%"
@wc -l "%OUT%"

@echo Finished at %TIME%
