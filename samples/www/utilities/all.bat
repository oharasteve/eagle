@setlocal

@if not "%1" == "" echo No directory please && goto :eof

@set STARTDIR=%~dp0

@rem cool trick!
@for /f "tokens=4 delims=/ " %%i in ('date /t') do set YR=%%i
@for /f "tokens=2 delims=/ " %%i in ('date /t') do set MON=%%i

@set ORIG=ERROR
@if %MON% == 01 set ORIG=a
@if %MON% == 02 set ORIG=a
@if %MON% == 03 set ORIG=a
@if %MON% == 04 set ORIG=a
@if %MON% == 05 set ORIG=b
@if %MON% == 06 set ORIG=b
@if %MON% == 07 set ORIG=b
@if %MON% == 08 set ORIG=b
@if %MON% == 09 set ORIG=c
@if %MON% == 10 set ORIG=c
@if %MON% == 11 set ORIG=c
@if %MON% == 12 set ORIG=c

@echo Processing %YR%-%MON% (%YR%%ORIG%)
@call "%STARTDIR%allo.bat" "..\originals\Year_%YR%\%YR%%ORIG%"
@call "%STARTDIR%allp.bat" "..\photos\year-%YR%\%YR%-%MON%"
