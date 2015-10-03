@setlocal

@rem THIS IS THE ONE FOR movies in the ORIGINALS folder!

@if "%1" == "" echo Need a directory && goto :eof

echo Processing %1 ...
call nospaces "%1"
call genmovies.bat "%1" > "%1\index.html"
