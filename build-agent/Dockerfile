FROM ubuntu:bionic

ARG DEBIAN_FRONTEND=noninteractive
ARG KUBECTL_VERSION=1.21.11
ARG HELM_VERSION=3.3.4
ARG HELM_DIFF_VERSION=3.1.1
ARG DOTNET_CORE_SDK_VERSION=3.1
ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
ARG HELMFILE_VERSION=v0.140.0

# upgrade
RUN \
  apt-get update \
  && apt-get install -y apt-utils 2>&1 | grep -v "not installed" \
  && apt-get upgrade -y \
  && apt-get clean

# keys
COPY secrets/pip.conf /root/.config/pip/pip.conf
COPY secrets/.pypirc /root/.pypirc
COPY secrets/.npmrc /root/.npmrc
COPY secrets/artifactory.credentials /root/.sbt/.credentials
COPY secrets/artifactory_maven_m2_settings.xml /root/.m2/settings.xml
COPY secrets/ /secrets/

# install packages
RUN \
  apt-get update --fix-missing && \
  apt-get install -y \
    apt-transport-https \
    automake \
    build-essential \
    ca-certificates \
    curl \
    git \
    iputils-ping \
    make \
    maven \
    net-tools \
    openjdk-8-jdk-headless \
    openssl \
    python3 \
    python3-pip \
    python3.7 \
    python3.7-dev \
    python3.7-distutils \
    unzip \
    vim \
    wget \
    rsync \
  && update-java-alternatives -a

# .net core sdk


# docker
ENV DOCKER_BUILDKIT=1
RUN \
  apt-get install -y software-properties-common \
  && curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - \
  && apt-get update \
  && add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" \
  && apt-get update --fix-missing \
  && apt-get install -y docker-ce=5:18.09.6~3-0~ubuntu-bionic



# helm 3
RUN wget -L https://get.helm.sh/helm-v$HELM_VERSION-linux-amd64.tar.gz -O helm.tar.gz \
  && tar -zxvf helm.tar.gz \
  && mv linux-amd64/helm /usr/local/bin/helm \
  && helm version \
  && helm plugin install https://github.com/databus23/helm-diff --version $HELM_DIFF_VERSION

# stable chrome
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
  && apt install -y ./google-chrome-stable_current_amd64.deb \
  && rm -f ./google-chrome-stable_current_amd64.deb

# aws cli
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
  && unzip awscliv2.zip \
  && ./aws/install \
  && rm awscliv2.zip





# OpenJDK 14
RUN add-apt-repository -y ppa:openjdk-r/ppa \
  && apt install -y openjdk-11-jdk openjdk-1-jdk \
  && update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-11-openjdk-amd64/bin/javac 1080 \
  && update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-11-openjdk-amd64/bin/java 1090 


  
# Maven 3.6.3
RUN  wget http://apache.mirrors.pair.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -O /tmp/apache-maven-3.6.3-bin.tar.gz \
  && tar xf /tmp/apache-maven-*.tar.gz -C /usr/share \
  && update-alternatives --install /usr/bin/mvn mvn /usr/share/apache-maven-3.6.3/bin/mvn 100


# helmfile
RUN curl -fsSLO https://github.com/roboll/helmfile/releases/download/$HELMFILE_VERSION/helmfile_linux_amd64 \
    && mv ./helmfile_linux_amd64 /usr/local/bin/helmfile \
    && chmod +x /usr/local/bin/helmfile \
    && helmfile version

