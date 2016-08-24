FROM openjdk:8-jre-alpine
RUN apk update
RUN apk add openjdk8-jre
ADD build/libs/microservice-0.1.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]