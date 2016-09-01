#Microservice

## Spring profiles
consul

# Development
echo "127.0.0.1 consul" | sudo tee -a /etc/hosts

## Run Registry
docker run -d -p 5000:5000 --name registry registry:2

## Run consul
docker run -d -p 8500:8500 --name consul consul agent -dev -client=0.0.0.0 -ui

### Microservice
gradle bootRun -PSPRING_PROFILES_ACTIVE=...

##test
curl http://localhost:8881/api/0.1/echo?message=hello | json
curl http://localhost:8500/v1/health/service/microservice
curl -D - http://...
curl --head http://...

## Docker`ing
./container-build.sh bytewood/ops/microservice 0
./container-push.sh localhost:5000 bytewood/ops/microservice 0

## Test registry
curl --head http://localhost:5000/v2
curl http://localhost:5000/v2/_catalog
curl http://localhost:5000/v2/bytewood/ops/microservice/tags/list
curl http://localhost:5000/v2/bytewood/ops/microservice/manifests/[0, latest]
