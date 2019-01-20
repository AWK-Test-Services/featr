FROM openjdk:8-jdk-alpine
MAINTAINER Arjan Kranenburg

COPY target/dctnry-server-*-exec.jar dctnry-server.jar
ENTRYPOINT ["java","-jar","/dctnry-server.jar"]
