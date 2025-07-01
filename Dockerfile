# ARM v7용 Java 17 Dockerfile
FROM arm32v7/openjdk:17-jdk-slim

WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]