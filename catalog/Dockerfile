FROM openjdk:8-jre-alpine
ADD target/catalog-*.jar /app.jar
CMD java $JAVA_MEM $JAVA_EXT -jar /app.jar
