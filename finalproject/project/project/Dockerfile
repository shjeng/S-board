FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar
ARG PPROFILES
ARG ENV
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${PPROFILES}","-Dserver.en=${ENV}","-jar","app.jar"]