#FROM openjdk:10-jdk-oraclelinux7
#COPY "./target/Ejemplo1-1.jar" "app.jar"
#EXPOSE 8019
#ENTRYPOINT [ "java", "-jar", "app.jar" ]
# Usar OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven/Gradle a la imagen
COPY "./target/Ejemplo1-1.jar" "app.jar"

# Exponer el puerto en el que correrá tu aplicación (asegúrate que coincida con el de tu aplicación)
EXPOSE 8090

# Comando para ejecutar la aplicación
ENTRYPOINT [ "java", "-jar", "app.jar" ]

