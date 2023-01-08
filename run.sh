#!/usr/bin/env bash
mvn package
clear
local_repo=$(mvn help:effective-settings | sed -n 's/.*localRepository>\(.*\)<\/localRepository.*/\1/p')
java -cp $local_repo/com/google/code/gson/gson/2.10/gson-2.10.jar:./target/dactylogame-1.0-SNAPSHOT.jar com.cpo.dactylogame.launcher.Launcher