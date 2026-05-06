# Bước 1: Build project với Maven sử dụng JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng Java với Runtime JDK 21
FROM eclipse-temurin:21-jdk-focal
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]