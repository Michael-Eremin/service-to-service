FROM maven:3.9.8-eclipse-temurin-21 as dependencies

# Copy prent pom.xml and service pom.xml
WORKDIR /app/service-to-service
COPY ./pom.xml .
COPY ./report-service/pom.xml ./report-service/pom.xml


RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml dependency:go-offline

# App build stage
FROM dependencies as build
WORKDIR /app/service-to-service/report-service

COPY ./report-service/src ./src

RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml -Dmaven.test.skip=true package

# Final build stage
FROM openjdk:21-slim
WORKDIR /app

# Add libreoffice package
RUN apt-get update && \
    apt-get install -y libreoffice && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*


COPY --from=build /app/service-to-service/report-service/target/*.jar Backend.jar

RUN mkdir -p /app/convert-test

EXPOSE 8083

CMD ["java", "-jar", "/app/Backend.jar"]