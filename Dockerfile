FROM openjdk:8-jdk-alpine
MAINTAINER sledesmadev
COPY target/atm-0.0.1-SNAPSHOT.jar ATM-SpringBoot-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/ATM-SpringBoot-app-1.0.0.jar"]