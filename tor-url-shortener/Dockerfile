FROM openjdk:17-jdk-slim

VOLUME /tmp

#host.docker.internal allows this docker container to know the ip adress of the localhost/
ENV MYSQL_URL="" 
ENV MYSQL_USER=""
ENV MYSQL_PASSWORD=""

COPY target/tor-url-shortener-0.0.1-SNAPSHOT.jar torurlshortener.jar
EXPOSE 8080
ENTRYPOINT ["exec", "java", "-jar", "torurlshortener.jar"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar torurlshortener.jar
