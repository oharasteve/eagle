@setlocal

@set SVN=C:\SVN\Eagle
@set GIT=C:\GIT\Eagle

@for %%i in ( programmar tokens ) do @(
    @echo Copying src\com\eagle\%%i ...
    @xcopy /f /e /v /r /h /i /d /y "%SVN%\Tools\src\com\eagle\%%i" "%GIT%\src\com\eagle\%%i"
)

@echo Copying Samples\Dave\src to samples\www
@xcopy /f /e /v /r /h /i /d /y "%SVN%\Samples\Dave\src" "%GIT%\samples\www"
