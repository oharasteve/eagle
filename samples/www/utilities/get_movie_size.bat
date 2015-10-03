@setlocal

@if "%~1" == "" echo Need a movie filename && goto :eof

@set FF=C:\Program Files\ffmpeg-git-9d4cb45-64-bit-static\bin\ffmpeg.exe
@set OUT=%TEMP%\get_movie_size.out

@"%FF%" -i "%~1" > "%OUT%" 2>&1
@grep "Duration" "%OUT%" | awk "{print $2};" | sed "s+,++;s+$+  %~nx1+"
