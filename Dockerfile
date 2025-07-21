# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/simulation-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]