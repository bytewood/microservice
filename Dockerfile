FROM alpine
RUN apk update
RUN apk add openjdk8-jre
ADD build/libs/microservice-0.1.jar /
ENTRYPOINT ["java -jar microservices-0.1.jar"]

