@setlocal

@set SVN=C:\SVN\Eagle
@set GIT=C:\GIT\Eagle

@rem Change this to ECHO to see what is happening, otherwise set it to REM
@set ECHOREM=rem

@set XOPTS=/f /s /v /r /h /i /d /y
@set FILTER=grep -v "0 File.s. copied"

@for %%i in ( programmar tokens ) do @(
    @%ECHOREM% Copying src\com\eagle\%%i ...
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\%%i" "%GIT%\src\com\eagle\%%i\" | %FILTER%
)

@%ECHOREM% Copying Samples\Dave\src to samples\www
@xcopy %XOPTS% "%SVN%\Samples\Dave\src" "%GIT%\samples\www\" | %FILTER%

@%ECHOREM% Copying src\com\eagle
@for %%i in ( EagleReadXML ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\%%i.java" "%GIT%\src\com\eagle\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\parsers
@for %%i in ( EagleFileReader EagleLineReader EagleOverrideManager EagleParseException ParserManager ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\parsers\%%i.java" "%GIT%\src\com\eagle\parsers\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\preprocess
@for %%i in ( EagleInclude FindIncludeFile ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\preprocess\%%i.java" "%GIT%\src\com\eagle\preprocess\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\project
@for %%i in ( EagleProject ProgramEntry ProjectEntry RepairFile ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\project\%%i.java" "%GIT%\src\com\eagle\project\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\tests
@for %%i in ( EagleInterpreter EagleRunnable EagleTestable EagleTests ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\tests\%%i.java" "%GIT%\src\com\eagle\tests\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\utils
@for %%i in ( EagleFile EaglePath EagleUtilities ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\utils\%%i.java" "%GIT%\src\com\eagle\utils\" | %FILTER%
)

@%ECHOREM% Copying the www parse results
@xcopy %XOPTS% "%SVN%\Samples\Dave\PARSED\*.xml" "%GIT%\samples\www\xml\" | %FILTER%

@%ECHOREM% Make all the files read-only, except the fake EagleParser.java
@attrib +r *.java /s
@attrib -r EagleParser.java /s
@attrib +r *.xml /s
