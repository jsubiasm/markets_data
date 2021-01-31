@echo off

set JAVA_HOME=C:\_JSM\SeguimientoCartera\01_Software\jdk-15.0.2_windows-x64_bin\jdk-15.0.2
PATH=%JAVA_HOME%\bin;%M2_HOME%\bin

java -jar .\target\SeguimientoCartera-0.0.1-SNAPSHOT-jar-with-dependencies.jar > run.log

pause
