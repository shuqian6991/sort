#!/bin/bash
#--------------------------------------------------------------------------
# Manage the version number for releases that will be archived to artifactory.
# This script is meant to be run only in the Jenkins environment. It appends the
# to the major and minor version numbers it finds in the gradle.properties
# file (see currentVersion).
#
# It is assumed that we're working with gradle and its artifactory plugin.
# The line that controls the numbering begins with "currentVersion"
# The build identifier we use will be in this format:
#
#     <Major version>.<Minor version>.<Build Number>-<Type>
#
# Major version - an integer, 1, 2, 3, ...
# Minor version - an integer, 1, 2, 3, ...
# Build Number  - 6 digit, zero-filled integer, 000001, 000002, ...
#--------------------------------------------------------------------------

if [ ! -f gradle.properties ]; then
    echo "File does not exist: gradle.properties"
    exit 1
fi
ver=$( grep currentVersion gradle.properties )
if [ -z "$ver" ]; then
    echo "File gradle.properties does not have an entry for currentVersion."
    echo "Have you pulled in the gradle artifactory plugin and set it up?"
    exit 1
fi

#
# $ver will look like this:
#	currentVersion=1.0.000000-SNAPSHOT
#                      | |    |      ^--- can be either SNAPSHOT or RELEASE
#                      | |    +---------- build number space
#                      | +--------------- Minor version number
#                      +----------------- Major version number
ver=${ver/currentVersion=/}
verlen=${#ver}

major=${ver/%.*/}
majorlen=${#major}
type=${ver/#*-/}
typelen=${#type}
len=$((verlen-typelen-1))
subver1=${ver:0:$len}
idx=$((1+majorlen))
subver2=${subver1:$idx}
minor=${subver2/%.*/}
buildno=${subver2/#*./}

if [  -z "${BUILD_NUMBER}" ]; then
    echo "BUILD_NUMBER environment variable is not set"
    exit 1
fi

buildno=$((BUILD_NUMBER))

echo "Updating build number information for this build:"
printf "Major: = %d\nMinor: = %d\nBuild No: %06d\nType: $type\n" $major $minor $buildno

printf "currentVersion=%d.%d.%06d-$type\n" $major $minor $buildno > tmpfile
sed '/currentVersion/d' gradle.properties >> tmpfile
mv gradle.properties gradle.properties.bak
mv tmpfile gradle.properties
