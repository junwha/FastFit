FROM ubuntu:20.04

# openjdk installation requires user interaction, so we need to suppress it
# https://serverfault.com/questions/949991/how-to-install-tzdata-on-a-ubuntu-docker-image
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && \
    apt-get install -y git && \
    apt-get install -y openjdk-11-jdk && \
    apt-get install -y maven

RUN mkdir -p /root/project

WORKDIR /root/project
COPY ./run.sh /root/project/


