# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3-openjdk-14-slim

ADD . /usr/src/app/
WORKDIR /usr/src/app/

# Run Maven build
RUN mvn clean install

# 2. Just using the build artifact and then removing the build-container
FROM openjdk:14-jdk-slim

ARG JAR_FILE=/usr/src/app/target/*.jar
COPY --from=0 ${JAR_FILE} app.jar

# Fire up our Spring Boot app by default 
CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]