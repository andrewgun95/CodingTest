FROM azul/zulu-openjdk-alpine:11-jre
ENV PORT 8080
EXPOSE 8080
COPY build/libs/*.jar /opt/app.jar
WORKDIR /opt
ENTRYPOINT ["sh","-c", "java $JAVA_OPTS -jar app.jar"]
