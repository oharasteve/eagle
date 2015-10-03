@setlocal

@set STARTDIR=%~dp0

@rem Let awk put commas in numbers!
@set LC_ALL=en_US.UTF-8
@set UP=%TEMP%\updatePhotoCount.sed

@grep "^INSERT INTO .Photos. " ..\family.sql | wc -l | awk -f "%STARTDIR%updatePhotoCount.awk" > "%UP%"
@echo s+@UPDATED@+%DATE% at %TIME%+ >> "%UP%"
@call "%STARTDIR%sizes.bat" > "%TEMP%\sizes.out"
@awk -f "%STARTDIR%sizes.awk" < "%TEMP%\sizes.out" >> "%UP%"
@sed -f "%UP%" < ..\index0.html > ..\index.html
@echo Updated ..\index.html
