@echo off

set JAVA_HOME=C:\_JSM\SeguimientoCartera\01_Software\jdk-15.0.2_windows-x64_bin\jdk-15.0.2
set M2_HOME=C:\_JSM\SeguimientoCartera\01_Software\apache-maven-3.6.3-bin\apache-maven-3.6.3
PATH=%JAVA_HOME%\bin;%M2_HOME%\bin

call mvn clean install

pause
