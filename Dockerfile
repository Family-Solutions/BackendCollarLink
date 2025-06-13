FROM openjdk:22-jdk
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar