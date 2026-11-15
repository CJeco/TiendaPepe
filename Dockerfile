# ==========================================
# ETAPA 1: Construcción de la aplicación con Gradle
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copiar el código fuente
COPY src src

# Compilar y generar el archivo JAR ejecutable
RUN ./gradlew bootJar --no-daemon

# ==========================================
# ETAPA 2: Imagen ligera para ejecución (JRE)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]