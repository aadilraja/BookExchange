# Stage 1: Build the application
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

# Accept build argument for port
ARG API_PORT=8080
ENV SERVER_PORT=${API_PORT}
ENV SPRING_PROFILES_ACTIVE=docker

# Create upload directory
RUN mkdir -p /app/uploads/images

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the configurable port
EXPOSE ${API_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]