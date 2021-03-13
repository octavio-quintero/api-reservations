FROM openjdk:8-jdk-alpine
MAINTAINER oquintero.com
COPY target/reservations.api-1.0.jar reservations.api-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/reservations.api-1.0.jar"]