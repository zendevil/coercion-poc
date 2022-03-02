FROM openjdk:8-alpine

COPY target/uberjar/coercion-poc.jar /coercion-poc/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/coercion-poc/app.jar"]
