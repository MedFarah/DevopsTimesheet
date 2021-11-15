FROM openjdk:11


WORKDIR /app
ADD target/*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/app.jar"]