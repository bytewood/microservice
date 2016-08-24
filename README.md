#Microservice

## Docker
### Connect to a container with failing ENTRYPOINT
docker run -it --entrypoint=/bin/sh ${image}

### Connect to a running container
docker exec -it ${container} /bin/bash
