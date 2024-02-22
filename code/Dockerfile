FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} weixin-java-mp-demo-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/weixin-java-mp-demo-1.0.0-SNAPSHOT.jar"]