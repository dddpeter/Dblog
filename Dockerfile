FROM --platform=linux/amd64 adoptopenjdk/openjdk8-openj9:alpine-jre
WORKDIR /usr/src/app
ADD  ./blog-web/build//libs/blog-web.jar  ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","${JVM_OPTS:=-Xms512m -Xmx512m}","-Dspring.profiles.active=${PROFILE:=prd}","-DLOGGING_LEVEL=${LOG_LEVEL:=debug}","./app.jar"]