FROM openjdk
WORKDIR usr/lib
ADD ./target/DockerizeUsers-0.0.1-SNAPSHOT.jar /usr/lib/DockerizeUsers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","DockerizeUsers-0.0.1-SNAPSHOT.jar"]