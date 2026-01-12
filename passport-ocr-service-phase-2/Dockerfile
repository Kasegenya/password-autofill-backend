## =========================
## Build stage
## =========================
#FROM maven:3.9.9-eclipse-temurin-21 AS build
#WORKDIR /build
#
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
#COPY src ./src
#RUN mvn clean package -DskipTests
#
## =========================
## Runtime stage
## =========================
#FROM eclipse-temurin:21-jre
#ENV DEBIAN_FRONTEND=noninteractive
#
#RUN apt-get update && \
#    apt-get install -y tesseract-ocr tesseract-ocr-eng && \
#    rm -rf /var/lib/apt/lists/*
#
#WORKDIR /app
#COPY --from=build /build/target/*.jar app.jar
#
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]


FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]