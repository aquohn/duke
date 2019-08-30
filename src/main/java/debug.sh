#!/usr/bin/env bash

make DEBUG=1
cd ../../../debug
jdb -classpath . -sourcepath ../src/main/java aquohn.duke.Duke
