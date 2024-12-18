
FROM openjdk:17-jdk
WORKDIR /app

COPY build/libs/foodHub-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]