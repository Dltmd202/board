FROM openjdk:17-ea-11-jdk-slim
COPY . /app
WORKDIR /app
RUN ./gradlew build -x test
ENTRYPOINT ["java", "-jar", "./build/libs/be-0.0.1-SNAPSHOT.jar"]
