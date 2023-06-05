#FROM openjdk:17-jdk
#
#WORKDIR /app
#
#COPY ./target/Blogify-0.0.1-SNAPSHOT.jar app.jar
#COPY ./src/main/resources/application.properties application.properties
#
#CMD ["java", "-jar", "app.jar"]

#
# Build stage
#
FROM openjdk:17-jdk AS build
COPY . .
#RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-jdk
COPY --from=build /target/Blogify-0.0.1-SNAPSHOT.jar blogify.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","blogify.jar"]

