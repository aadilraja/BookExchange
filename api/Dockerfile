# Stage 1: Build the application
# This stage uses a Maven image based on Eclipse Temurin to build the application.
FROM maven:3.9.6-eclipse-temurin-17 AS build

LABEL authors="aadil"

WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests


# Stage 2: Create the final, smaller image

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=docker

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
