FROM openjdk:8-jre-alpine
ADD build/libs/app.jar /app.jar
EXPOSE 8881
ENTRYPOINT ["java", "${JAVA_OPTS}", "-jar", "/app.jar"]
