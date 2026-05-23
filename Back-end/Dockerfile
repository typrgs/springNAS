FROM gradle:jdk17 AS builder
WORKDIR /
COPY . .
RUN gradle clean bootjar

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /build/libs/Back-end.jar .
ENTRYPOINT [ "java", "-jar", "Back-end.jar" ]