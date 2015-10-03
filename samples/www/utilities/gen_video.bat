@rem This is for videos in the videos folder

@dir "%~1" | awk -f gen_video.awk -v "DIR=%~nx1" > "%~1\index.html"
@echo Finished creating %~1\index.html
