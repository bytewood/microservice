#Microservice

#run
./gradlew clean build bootRun

#test
curl http://localhost:8881/api/echo?message=hello

###
TODO add artifact repository credentials
sudo usermod -aG docker jenkins