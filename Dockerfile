FROM openjdk:17-jdk-alpine
LABEL authors="Lukasz&Tomek"

WORKDIR /app

COPY target/Connect-1.jar /app/Connect-1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Connect-1.jar"]