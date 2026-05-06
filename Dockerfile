# Bước 1: Build project với Maven sử dụng JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng Java sử dụng JRE/JDK 21 (Bản Jammy phổ biến hơn)
FROM eclipse-temurin:21-jdk
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]