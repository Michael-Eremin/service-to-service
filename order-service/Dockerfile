# Этап для загрузки зависимостей
FROM maven:3.9.8-eclipse-temurin-21 as dependencies

# Копируем родительский pom.xml и pom.xml микросервиса
WORKDIR /app/service-to-service
COPY ./pom.xml .
COPY ./order-service/pom.xml ./order-service/pom.xml

# Загружаем зависимости, используя родительский pom.xml
RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml dependency:go-offline

# Этап для сборки приложения
FROM dependencies as build
WORKDIR /app/service-to-service/order-service

# Проверка структуры файлов и директорий
RUN ls -la && echo "11111"

# Копируем исходный код микросервиса
COPY ./order-service/src ./src

# Собираем приложение, используя родительский pom.xml
RUN --mount=type=cache,target=$HOME/.m2 mvn -f /app/service-to-service/pom.xml -Dmaven.test.skip=true package

# Финальный этап
FROM openjdk:21-slim
WORKDIR /app

# Копируем собранный JAR-файл
COPY --from=build /app/service-to-service/order-service/target/*.jar Backend.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/Backend.jar"]