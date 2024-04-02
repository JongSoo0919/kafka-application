FROM gradle:8.5.0-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./

RUN gradle dependencies --no-daemon

COPY . /app

RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/product-service.jar

EXPOSE 8002
ENTRYPOINT ["java"]
CMD ["-jar", "product-service.jar"]