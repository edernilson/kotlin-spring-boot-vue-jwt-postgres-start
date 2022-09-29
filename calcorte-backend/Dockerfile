FROM adoptopenjdk:11-jre-hotspot as builder
MAINTAINER "edernilson/calcorte-api"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "org.springframework.boot.loader.JarLauncher"]