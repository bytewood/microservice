#!/bin/bash

version=`cat gradle.properties | grep ops-scripts_version | awk -F = '{print $2}'`

zipfile="$version.zip"
folder="ops-scripts"
mkdir -p "$folder"
cd "$folder"

wget -O "$zipfile" "https://github.com/bytewood/ops-scripts/archive/$version.zip"
unzip -j "$zipfile"
rm -f "$zipfile"

