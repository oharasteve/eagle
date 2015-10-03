@if "%1" == "" echo Directory is required && goto :eof
@dir /s "%1\*.jpg" | grep -e "[3-9][0-9][0-9]," | grep -v bytes
@dir /s "%1\*.jpg" | grep -e ",.*," | grep -v bytes



