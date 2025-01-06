FROM maven:3.9.8-eclipse-temurin-21 as dependencies

# Copy prent pom.xml and service pom.xml
WORKDIR /app/service-to-service
COPY ./pom.xml .
COPY ./stock-service/pom.xml ./stock-service/pom.xml
#COPY ./order-service/pom.xml ./order-service/pom.xml
#COPY ./report-service/pom.xml ./report-service/pom.xml


RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml dependency:go-offline

# App build stage
FROM dependencies as build
WORKDIR /app/service-to-service/stock-service


COPY ./stock-service/src ./src


RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml -Dmaven.test.skip=true package

# Final build stage
FROM openjdk:21-slim
WORKDIR /app

# Копируем собранный JAR-файл
COPY --from=build /app/service-to-service/stock-service/target/*.jar Backend.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/Backend.jar"]