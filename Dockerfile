FROM openjdk:13-alpine
MAINTAINER omirandam
WORKDIR /app
COPY ./build/libs/msRentHouse-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]
