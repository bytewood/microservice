FROM openjdk:8-jre-alpine
ADD build/libs/app.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]