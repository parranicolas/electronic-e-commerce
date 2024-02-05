FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/products-service-0.0.1.jar
COPY ${JAR_FILE} products.jar
ENTRYPOINT ["java", "-jar", "products.jar"]