FROM openjdk:17-jdk-slim

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

# for build docker image: docker build -t spring-java-app .
# for run app in docker container: docker run -p 8080:8080 -t spring-java-app