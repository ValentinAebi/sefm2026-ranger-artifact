FROM ubuntu:26.04

## to run APT without user input
ARG DEBIAN_FRONTEND="noninteractive"
ENV TZ="Europe/Zurich"

RUN \
  apt update && \
  apt -y --no-install-recommends install \
    openjdk-25-jdk \
    maven \
    python3 \
    curl \
    bash \
    gzip

# Scala
RUN curl -fL https://github.com/coursier/coursier/releases/latest/download/cs-x86_64-pc-linux.gz \
    | gzip -d > /usr/local/bin/cs \
    && chmod +x /usr/local/bin/cs
RUN yes | cs install scala:3.8.2 scalac:3.8.2 --install-dir /usr/local/bin

ENV JAVA_HOME="/usr/lib/jvm/java-25-openjdk-amd64"

COPY . /opt/ranger-examples

WORKDIR "/opt/ranger-examples"
