@ECHO OFF

REM create bin and data directories if needed
if not exist ..\bin mkdir ..\bin
if not exist ..\data mkdir ..\data

REM delete output from previous run
if exist test.out del test.out
if exist "../data/tasks.tsv" del "../data/tasks.tsv"

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
   echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output
REM to test.out
java -classpath ..\bin Duke < Level-8.in > test.out

REM test recovery of corrupted file
echo "file corrupted!" > ../data/tasks.tsv
java -classpath ..\bin Duke < n >> test.out

REM test deletion of corrupted file
echo "file corrupted!" > ../data/tasks.tsv
java -classpath ..\bin Duke < ybye >> test.out

REM compare the output to the expected output
FC Level-8.out test.out
