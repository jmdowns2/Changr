FROM openjdk:8-jdk
ARG JAR_FILE

COPY build.docker.sh /build.docker.sh
COPY scripts /scripts
RUN /build.docker.sh

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]