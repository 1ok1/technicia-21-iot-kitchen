#
# Build stage
#
### BUILD image
FROM maven:3.8.3-jdk-8 as build
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package

FROM openjdk:11-jre-slim
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Xmx1g","-Xmx500m","-jar","app.jar"]
