FROM bellsoft/liberica-openjdk:21.0.3-13.armv7
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
