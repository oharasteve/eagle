@setlocal

@set STARTDIR=%~dp0

@dir /s ..\originals | awk -f "%STARTDIR%count_originals.awk" > "%TEMP%\count_originals.sed"
@sed -f "%TEMP%\count_originals.sed" < ..\originals\index0.html > ..\originals\index.html

@echo.
@echo n ..\originals\index.html
