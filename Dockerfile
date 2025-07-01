# Java 21 기반 slim 이미지 사용
FROM eclipse-temurin:21-jdk-jammy

# 애플리케이션 JAR 파일 복사
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
