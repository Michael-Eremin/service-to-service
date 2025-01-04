#FROM openjdk:21-slim
#WORKDIR /app
#COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "order-service.jar"]


# dependencies part
FROM maven:3.9.8-eclipse-temurin-21 as dependencies

COPY pom.xml .
COPY order-service .
RUN --mount=type=cache,target=$HOME/.m2 mvn dependency:go-offline

# builder stage for building the application
FROM dependencies as build
WORKDIR /app
COPY ./order-service/src src
RUN --mount=type=cache,target=$HOME/.m2 mvn -Dmaven.test.skip=true package

# final stage
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/*.jar Backend.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/Backend.jar"]