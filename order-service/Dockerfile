FROM gradle:8.5.0-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./

RUN gradle dependencies --no-daemon

COPY . /app

RUN gradle clean build --no-daemon

FROM --platform=linux/amd64 openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/order-service.jar

EXPOSE 8001
ENTRYPOINT ["java"]
CMD ["-jar", "order-service.jar"]