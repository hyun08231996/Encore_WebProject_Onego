# maven이미지에 mvn package로 *.jar 파일 생성
FROM maven:3.8-jdk-11-slim AS builder
WORKDIR /app
COPY ./ ./
RUN mvn package

# openjdk 이미지에 builder에서 생성된 *.jar파일을 복사
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]