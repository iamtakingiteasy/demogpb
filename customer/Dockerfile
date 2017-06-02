FROM openjdk:8-jre-alpine
ADD target/customer-*.jar /app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar