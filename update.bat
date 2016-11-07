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

@%ECHOREM% Copying src\com\eagle\io
@for %%i in ( EagleReadXML EagleReader EaglePrinter AbbreviateClassName DumpTree ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\io\%%i.java" "%GIT%\src\com\eagle\io\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\math
@xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\math" "%GIT%\src\com\eagle\math\" | %FILTER%

@%ECHOREM% Copying src\com\eagle\parsers
@for %%i in ( EagleFileReader EagleLineReader EagleOverrideManager EagleParseException EagleTracer ParserInterface ParserManager UnexpectedCommentManager UnparsedTokenManager UnusualTokenManager ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\parsers\%%i.java" "%GIT%\src\com\eagle\parsers\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\preprocess
@for %%i in ( EagleInclude EagleSymbolTable FindIncludeFile SavePreprocessedFile ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\preprocess\%%i.java" "%GIT%\src\com\eagle\preprocess\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\preprocess\C
@for %%i in ( CMacro_Preprocess ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\preprocess\C\%%i.java" "%GIT%\src\com\eagle\preprocess\C\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\project
@for %%i in ( EagleProject ProgramEntry ProjectEntry RepairFile ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\project\%%i.java" "%GIT%\src\com\eagle\project\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\softwarehouse
@for %%i in ( FileInventory PercentComplete ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\softwarehouse\%%i.java" "%GIT%\src\com\eagle\softwarehouse\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\tests
@for %%i in ( EagleInterpreter EagleRunnable EagleTestable EagleTests ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\tests\%%i.java" "%GIT%\src\com\eagle\tests\" | %FILTER%
)

@%ECHOREM% Copying src\com\eagle\utils
@for %%i in ( EaglePath EagleUtilities ) do @(
    @xcopy %XOPTS% "%SVN%\Tools\src\com\eagle\utils\%%i.java" "%GIT%\src\com\eagle\utils\" | %FILTER%
)

@%ECHOREM% Copying the www parse results
@xcopy %XOPTS% "%SVN%\Samples\Dave\PARSED\*.xml" "%GIT%\samples\www\xml\" | %FILTER%

@%ECHOREM% Make all the files read-only, except the fake EagleParser.java
@attrib +r *.java /s
@for %%i in ( EagleParser ParserCache ) do @(
    @attrib -r "%GIT%\src\com\eagle\parsers\%%i.java"
)
@attrib +r *.xml /s
