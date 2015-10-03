@setlocal

@dir /b ..\originals > \temp\originals.txt
@for /f %%i in ( \temp\originals.txt ) do @(
  if not "%%i" == "index.php" @(
    echo Processing %%i ...
    call genhtml.bat ..\originals\%%i > ..\originals\%%i\index.html
  )
)