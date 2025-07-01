FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY build/libs/*.jar app.jar

# 메모리 제한 추가
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-XX:MaxRAMPercentage=50.0", "-jar", "/app/app.jar"]