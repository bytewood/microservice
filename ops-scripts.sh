#!/bin/bash

version=$1
[ -z $verions ] && version=0.1.0

zipfile="$version.zip"
folder="ops-scripts"
mkdir -p "$folder"
cd "$folder"

wget -O "$zipfile" "https://github.com/bytewood/ops-scripts/archive/$version.zip"
unzip -j "$zipfile"
rm -f "$zipfile"

