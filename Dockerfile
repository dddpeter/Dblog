FROM --platform=linux/amd64 adoptopenjdk/openjdk8-openj9:alpine-jre
WORKDIR /usr/src/app
ADD  ./blog-web/build//libs/blog-web.jar  ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","${JVM_OPTS:=-Xms512m -Xmx512m -server -XX:+UseG1GC  -XX:+UseLWPSynchronization  -XX:+UseStringDeduplication -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/home/dddpeter/app/logs/java_pid<pid>.hprof -XX:+UseGCOverheadLimit}","-jar","-Dspring.profiles.active=${PROFILE:=prd}","-DLOGGING_LEVEL=${LOG_LEVEL:=debug}","./app.jar"]