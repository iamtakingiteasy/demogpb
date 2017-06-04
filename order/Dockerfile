FROM openjdk:8-jre-alpine
EXPOSE 8083
ADD target/order-*.jar /app.jar
CMD java $JAVA_MEM $JAVA_EXT -jar /app.jar
