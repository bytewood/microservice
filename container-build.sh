#!/usr/bin/env bash
repository=$1
tag=$2

usage() {
    echo "Usage:"
    echo "./docker-build.sh docker_image tag"
    exit 1
}

[ -z "${repository}" ] && usage
[ -z "${tag}" ] && usage

docker build -t ${repository}:${tag} -t ${repository}:latest .

