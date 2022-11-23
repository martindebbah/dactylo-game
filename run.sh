#!/usr/bin/env bash
mvn package
clear
java -cp ./target/dactylogame-1.0-SNAPSHOT.jar com.cpo.dactylogame.launcher.Launcher