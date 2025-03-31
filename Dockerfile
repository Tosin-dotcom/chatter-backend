FROM gradle:8.7.0-jdk17 AS build
WORKDIR /home/gradle/project
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar

FROM openjdk:19-jdk-alpine
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8433
ENTRYPOINT ["java","-jar","/app/app.jar"]
