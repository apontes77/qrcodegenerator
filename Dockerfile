FROM gradle:8.4-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY src src

RUN gradle build --no-daemon

FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

ENV AWS_REGION=us-east-1
ENV AWS_BUCKET_NAME=qrcode-storage-vnfc

# Expõe a porta (ajuste conforme sua aplicação)
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
