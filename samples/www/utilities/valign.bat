@setlocal

@echo This takes a few minutes to run, and will print to stdout
@echo Starting at %TIME%

@set OUT=%TEMP%\valign.out
@if exist %OUT% del /f %OUT% > %TEMP%\delme.out

@pushd ..
@for /r %%i in ( *.php *.html ) do @find /i /c "valign" "%%i" >> "%OUT%"
@find ":" "%OUT%" | find /v ": 0" | find /v "STEVE_UTSA" | find /v "SNIOC" | find /v "RRCC"
@popd

@echo Finished at %TIME%
