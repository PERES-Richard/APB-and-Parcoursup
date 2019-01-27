#! /bin/sh

mvn -q exec:java -Dexec.args="$*"
