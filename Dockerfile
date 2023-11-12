FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY gradle gradle

COPY gradlew .

COPY build.gradle build.gradle

COPY settings.gradle .

COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew clean build

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder ./app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]