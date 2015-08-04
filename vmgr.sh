#!/bin/bash
# Manage the version number for releases that will be archived to artifactory.
# It assumes that the BUILD_NUMBER environment variable contains the build
# number.  This environment variable is managed by Jenkins.  It also assumes
# that we're working with gradle and its artifactory plugin.  The line that
# controls the numbering begins with "currentVersion"

# $ver will look like this:
#	currentVersion=1.0.000000-SNAPSHOT
#                      | |    |      ^--- can be either SNAPSHOT or RELEASE
#                      | |    +---------- build number space
#                      | +--------------- Minor version number
#                      +----------------- Major version number
ver=$( grep currentVersion gradle.properties )
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
