@setlocal

@set J=C:\Program Files\Java\jdk1.8.0_72\bin\javac.exe
@set JUNIT=C:\Program Files\junit4.8.2\junit-4.8.2.jar

@rmdir /s /q bin
@mkdir bin

@set FILES=%TEMP%\files.list
@if exist "%FILES%" del "%FILES%"
@for /r src %%i in ( *.java ) do @echo %%i >> "%FILES%"

@rem this fails
@"%J%" -classpath "%JUNIT%" -sourcepath src -d bin -g @"%FILES%"

@rem and so does this
@"%J%" -classpath "%JUNIT%" -sourcepath src -d bin -g src\Sample.java
