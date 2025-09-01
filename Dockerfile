FROM alpine/java:21-jdk
ADD . /bin
WORKDIR /bin
RUN ["mvnw","clean","package","-DskipTests"]
WORKDIR /bin/target
EXPOSE 4567
ENTRYPOINT ["java","-jar","aggregator-1.jar"]
