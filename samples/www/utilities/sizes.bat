@echo Pictures (jpg, gif, etc)
@dir /s ..\*.gif ..\*.jpg ..\*.jpeg ..\*.bmp ..\*.png ..\*.tiff | tail -4 | grep "File.s"

@echo Movies (mpg, avi, etc)
@dir /s ..\*.mpg ..\*.mpeg ..\*.avi ..\*.mov ..\*.wmv ..\*.mp4 ..\*.MTS | tail -4 | grep "File.s"

@echo Music (mp3, etc)
@dir /s ..\*.mp3 | tail -4 | grep "File.s"

@echo Text (html, txt, pdf, etc)
@dir /s ..\*.htm ..\*.html ..\*.txt ..\*.pdf | tail -4 | grep "File.s"

@echo Total
@dir /s .. | tail -3
