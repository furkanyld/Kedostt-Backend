FROM maven:3.9.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DsipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Kedostt-Backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
