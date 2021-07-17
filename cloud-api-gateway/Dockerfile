FROM openjdk:16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-api-gateway.jar
ENTRYPOINT ["java","-jar","/cloud-api-gateway.jar"]
EXPOSE 8765