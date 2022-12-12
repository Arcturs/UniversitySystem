FROM maven:3.8.3-openjdk-17-slim as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /opt/build/
COPY pom.xml /opt/build/
COPY src /opt/build/src/
RUN mvn -f /opt/build/pom.xml clean package -DskipTests


FROM openjdk:17-alpine
WORKDIR /opt/app/
COPY --from=BUILDER /opt/build/target/*.jar /opt/app/app.jar

RUN apk --no-cache add curl

EXPOSE 8080

ENV TRACKER_DIR=/files
VOLUME ${TRACKER_DIR}

ENTRYPOINT ["java","-jar", "/opt/app/app.jar"]