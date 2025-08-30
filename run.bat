@echo off
setlocal
set "SCRIPT_DIR=%~dp0"
set "JAR=%SCRIPT_DIR%RSC-Single-Player.jar"
if not exist "%JAR%" set "JAR=%SCRIPT_DIR%rsc.jar"

java -jar "%JAR%" %*
endlocal

