
FROM java:8-alpine

ARG JAR_FILE

ADD target/${JAR_FILE} batchdemo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/batchdemo-0.0.1-SNAPSHOT.jar"]