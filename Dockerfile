# https://docs.docker.com/engine/reference/builder/#entrypoint
# initializes a new build stage and sets the Base Image
FROM openjdk:8-jdk-alpine
# declare target location
ARG JAR_FILE=target/*.jar
# jar from target directory
COPY ${JAR_FILE} library-management-system-0.0.1-SNAPSHOT.jar
# configure a container that will run as an executable
ENTRYPOINT ["java","-jar","/library-management-system-0.0.1-SNAPSHOT.jar"]