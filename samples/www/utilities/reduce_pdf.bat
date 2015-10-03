@setlocal

@set STARTDIR=%~dp0

@if "%~2" == "" echo Need a jpg name and a size && goto :eof

@rem Argh!
@set REDUCED=%~d1%~p1reduced\%~n1%~x1

@rem Create the smaller version
@set U=%STARTDIR%\ThumbNailer
@"java" -Xmx512M -classpath "%U%" ThumbNailer -size "%~2" -sameName "%~1"

@rem Smash it back on top
@copy /y "%REDUCED%" "%~1"
@del /f "%REDUCED%"
