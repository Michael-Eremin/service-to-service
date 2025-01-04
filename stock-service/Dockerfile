FROM openjdk:21-slim
WORKDIR /app
COPY target/stock-service-0.0.1-SNAPSHOT.jar stock-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "stock-service.jar"]