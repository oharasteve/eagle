@setlocal

@set FromDir=..\Dox\People
@set ToDir=\Google Drive\People

@for %%i in ( BLORT Harry_McCaffrey Mary_E_OHara Uncle_Harry ) do @(
    @echo Processing %%i
    @xcopy "%FromDir%\%%i" "%ToDir%\%%i" /EXCLUDE:skip.google /f /e /v /r /h /i /d /y
    @echo.
)
