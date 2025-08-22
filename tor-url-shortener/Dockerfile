FROM openjdk:17-jdk-slim

#Make sure to delete VOLUME /tmp since that makes it impossible to render
# Install dockerize
RUN apt-get update && apt-get install -y wget && \
    wget https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz && \
    tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.7.0.tar.gz


COPY target/tor-url-shortener-0.0.1-SNAPSHOT.jar torurlshortener.jar
EXPOSE 8080
ENTRYPOINT ["dockerize", "-wait", "tcp://database:3306", "-wait", "tcp://redis:6379", "-timeout", "30s", "java", "-jar", "torurlshortener.jar"]

# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar torurlshortener.jar
