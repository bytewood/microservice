#!/bin/bash

version="$1"

zipfile="${version}.zip"
folder="ops-scripts"
mkdir -p "$folder"
cd "$folder"

wget -O "$zipfile" "https://github.com/bytewood/ops-scripts/archive/${version}.zip"
unzip -o -j "$zipfile"
rm -f "$zipfile"
