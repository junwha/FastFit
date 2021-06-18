FROM ubuntu:20.04

# openjdk installation requires user interaction, so we need to suppress it
# https://serverfault.com/questions/949991/how-to-install-tzdata-on-a-ubuntu-docker-image
ENV DEBIAN_FRONTEND noninteractive

# Open 8080 port
EXPOSE 8080

RUN apt-get update && \
    apt-get install -y openjdk-11-jdk

RUN apt-get install -y maven git curl wget && \
    apt-get install -y gnupg


ENV MONGO_VERSION=4.4.6 \
    MONGO_MAJOR=4.4 \
    MONGO_PACKAGE=mongodb-org \
    MONGO_REPO=repo.mognodb.org

RUN wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add - && \
    echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-4.4.list && \
    set -x && \
    apt-get update && \
    apt-get install -y \
        ${MONGO_PACKAGE}=$MONGO_VERSION \
        ${MONGO_PACKAGE}-server=$MONGO_VERSION \
        ${MONGO_PACKAGE}-shell=$MONGO_VERSION \
        ${MONGO_PACKAGE}-mongos=$MONGO_VERSION \
        ${MONGO_PACKAGE}-tools=$MONGO_VERSION \
    && rm -rf /var/lib/apr/lists/* \
    && rm -rf /var/lib/mongodb \
    && mv /etc/mongod.conf /etc/mognod.conf.orig

RUN mkdir -p /data/db /data/configdb && \
    chown -R mongodb:mongodb /data/db /data/configdb

VOLUME /data/db /data/configdb
EXPOSE 27017


RUN mkdir -p /root/tomcat/ && cd /root/tomcat && \
    mkdir -p /root/project && \
    wget https://mirror.navercorp.com/apache/tomcat/tomcat-9/v9.0.46/bin/apache-tomcat-9.0.46.tar.gz && \
    tar -xvf apache-tomcat-9.0.46.tar.gz

# Set WORKDIR
WORKDIR /root/project

# Deploy
COPY ./run.sh /root/project/
COPY ./cse364-project.war /root/tomcat/apache-tomcat-9.0.46/webapps/ROOT.war

RUN rm -rf /root/tomcat/apache-tomcat-9.0.46/webapps/ROOT && \
    chmod +x /root/tomcat/apache-tomcat-9.0.46/bin/catalina.sh

#CMD ["mongo", "host.docker.internal:27017"]

CMD mongod --bind_ip=0.0.0.0 --fork --logpath /var/log/mongodb.log; /root/tomcat/apache-tomcat-9.0.46/bin/catalina.sh run
