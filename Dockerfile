# Stage 1: Build the application
FROM gradle:7.3.3-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew assemble --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
