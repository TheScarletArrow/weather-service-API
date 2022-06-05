#FROM java:8
FROM adoptopenjdk/openjdk15

ADD target/lab-api-0.0.9-metrics.jar my-app.jar
ENTRYPOINT ["java","-jar","my-app.jar"]