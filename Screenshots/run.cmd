@echo off

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set M2_HOME=C:\_PELAYO\Software\Maven\apache-maven-3.3.9
PATH=%JAVA_HOME%\bin;%M2_HOME%\bin

java -jar .\target\Screenshots-0.0.1-SNAPSHOT-jar-with-dependencies.jar > _Screenshots.log

pause
