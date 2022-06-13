#FROM java:8
FROM adoptopenjdk/openjdk15
EXPOSE 8080
ADD target/lab-api-0.0.13.jar my-app.jar
ENTRYPOINT ["java","-jar","my-app.jar"]