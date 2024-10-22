FROM openjdk:10-jdk-oraclelinux7
COPY "./target/Ejemplo1-1.jar" "app.jar"
EXPOSE 8019
ENTRYPOINT [ "java", "-jar", "app.jar" ]