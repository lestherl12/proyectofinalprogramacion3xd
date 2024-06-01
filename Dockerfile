# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Añadir un volumen apuntando a /tmp
VOLUME /tmp

# Copiar el archivo JAR de la aplicación al contenedor
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Copiar el archivo .env al contenedor
ARG ENV_FILE
COPY ${ENV_FILE} /.env

# Exponer el puerto que la aplicación usará
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]