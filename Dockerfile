FROM gradle:7.3.3-jdk17 AS builder
WORKDIR /opt/app
COPY ./build/libs/Individual_Project_V1-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c","java ${JAVA_OPTS} -jar Individual_Project_V1-1.0-SNAPSHOT.jar"]