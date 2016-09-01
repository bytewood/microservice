#!/bin/sh
registry="$1"
repository="$2"
tag="$3"

usage() { 
    echo "Usage:"
    echo "./docker-push.sh <registry> <repository> <tag>"
    echo "e.g."
    echo "./docker-push.sh localhost:500 bytewood/ops/microservice 999"
    exit 1
}

[ -z "${registry}" ] && usage
[ -z "${repository}" ] && usage
[ -z "${tag}" ] && usage

docker tag "${repository}:${tag}" "${registry}/${repository}:${tag}"
docker tag "${repository}:latest" "${registry}/${repository}:latest"
docker push "${registry}/${repository}:${tag}"
docker push "${registry}/${repository}:latest"

