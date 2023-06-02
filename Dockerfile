
#FROM ubuntu:latest
#
#RUN apt-get update && apt-get install -y openjdk-17-jdk maven
#
#WORKDIR /app
#
#COPY . .
#
#RUN mvn package
#CMD ["java", "-jar", "target/Blogify.jar"]


#FROM openjdk:17-jdk
#
#WORKDIR /app
#
#COPY . .
#
#CMD ["java", "-jar", "target/Blogify.jar"]

FROM openjdk:17-jdk

WORKDIR /app

COPY ./target/Blogify-0.0.1-SNAPSHOT.jar app.jar
COPY ./src/main/resources/application.properties application.properties

CMD ["java", "-jar", "app.jar"]

