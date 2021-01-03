FROM openjdk:16-jdk-alpine

RUN apk update && apk add --no-cache bash

# add snappy dependency in docker image for kafka
RUN apk add --no-cache libc6-compat
RUN ln -s /lib/libc.musl-x86_64.so.1 /lib/ld-linux-x86-64.so.2

MAINTAINER zmyzheng.io

ARG JAR_FILE
ARG APP_NAME

ENV USER_NAME zmyzheng
ENV APP_HOME /home/$USER_NAME/TweetMap/${APP_NAME}


RUN addgroup -S appgroup && adduser -S $USER_NAME -G appgroup

USER $USER_NAME

RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
RUN mkdir config
RUN mkdir logs
COPY ${JAR_FILE} ${JAR_FILE}
RUN ln -s ${JAR_FILE} app-server.jar

ENTRYPOINT ["java", "-jar", "app-server.jar"]
#ENTRYPOINT ["/usr/bin/java", "-jar", "app-server.jar", ">/dev/null", "2>&1"]
