@setlocal

@echo Starting at %TIME%

@set STARTDIR=%~dp0

@pushd "%STARTDIR%..\statistics"
@call "update.bat"
@popd

@echo Finished at %TIME%
