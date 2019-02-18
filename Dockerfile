FROM openjdk:8-jdk-alpine
MAINTAINER Arjan Kranenburg

RUN apk update && apk add bash git openssh

RUN mkdir -p /repos

COPY target/featr-server-*-exec.jar featr-server.jar
#ENTRYPOINT ["java","-jar","/featr-server.jar"]
ENTRYPOINT ["java","-jar","/featr-server.jar","--spring.config.location=file:/application.yml"]
