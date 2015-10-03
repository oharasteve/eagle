@setlocal

@set SVN=C:\SVN\Eagle\Tools
@set GIT=C:\GIT\Eagle

@for %%i in ( programmar tokens ) do @(
    @echo Copying src\com\eagle\%%i ...
    @xcopy /f /e /v /r /h /i /d /y "%SVN%\src\com\eagle\%%i" "%GIT%\src\com\eagle\%%i"
)