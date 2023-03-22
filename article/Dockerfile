# Use the official OpenJDK image as the base image
FROM openjdk:17

# Set the working directory inside the container
VOLUME /tmp
COPY target/*.jar article-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/article-0.0.1-SNAPSHOT.jar"]
