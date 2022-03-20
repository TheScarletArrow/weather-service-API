#FROM java:8
FROM adoptopenjdk/openjdk15
WORKDIR /myapp
COPY target/lab-api-0.0.1-SNAPSHOT.jar /myapp/my-app.jar
#ADD /main/resources/application.properties /myapp/application.properties
ENTRYPOINT ["java","-jar","my-app.jar"]