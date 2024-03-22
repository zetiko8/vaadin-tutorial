FROM maven:3.8.6-openjdk-8-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/tutorial-1.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "tutorial-1.0.1-SNAPSHOT.jar"]