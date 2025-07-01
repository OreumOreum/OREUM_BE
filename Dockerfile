# ARM v7용 Java 17 Dockerfile (대안 1 - 멀티 아키텍처 이미지 사용)
FROM eclipse-temurin:17-jdk

WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]