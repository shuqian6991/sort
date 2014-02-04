#!/bin/sh
################################################################
#
# A very simple, brute-force make script for these java sources.
# Steve Mansour
################################################################
if [ ! -d "target" ]; then
    mkdir target
fi
if [ "$JAVAEXTERN" == "" ]; then
    echo "WARNING: environment variable JAVAEXTERN is not set"
    exit 1;
fi

if [ $# -gt 0 ]; then
    case "$1" in
	clean)	echo "cleaning..."; shift; rm target/*.class  ;;
	*)  echo "unknown option: $1"; shift ;;
    esac
else
    javac -cp "${JAVAEXTERN}/junit-4.11.jar" src/main/*.java src/test/*.java -g -d target 
fi
