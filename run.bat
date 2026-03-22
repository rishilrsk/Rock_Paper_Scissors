@echo off
echo Compiling Rock-Paper-Scissors Game...

if not exist bin mkdir bin

javac -d bin src/rps/Main.java src/rps/logic/GameLogic.java src/rps/ui/GameUI.java

if %ERRORLEVEL% equ 0 (
    echo Compilation Successful! Starting the game...
    java -cp bin rps.Main
) else (
    echo Compilation Failed.
)

pause
