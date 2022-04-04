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
RUN wget https://packages.microsoft.com/config/ubuntu/18.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb \
  && dpkg -i packages-microsoft-prod.deb \
  && apt-get update \
  && apt-get install -y dotnet-sdk-$DOTNET_CORE_SDK_VERSION

# sbt
RUN \
  echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list \
  && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list \
  && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add \
  && apt-get update \
  && apt-get install sbt \
  && java -version \
  && mkdir -p sbt/project \
  && cd sbt \
  && (echo "sbt.version=1.6.1" > project/build.properties && sbt 'set scalaVersion := "2.13.6"' compile && rm -r project/target target) \
  && rm -r /sbt

# pip3.7
RUN \
  mv /root/.config/pip/pip.conf /root/.config/pip/pip.conf.bak \
  && python3.7 -m pip -v install --upgrade pip \
  && python3.7 -m pip -v install --upgrade \
    buildtools \
    docker \
    keyrings.alt \
    pytest \
    setuptools \
    twine \
    wheel \
  && pip3.7 --version \
  && mv /root/.config/pip/pip.conf.bak /root/.config/pip/pip.conf

# docker
ENV DOCKER_BUILDKIT=1
RUN \
  apt-get install -y software-properties-common \
  && curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - \
  && apt-get update \
  && add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" \
  && apt-get update --fix-missing \
  && apt-get install -y docker-ce=5:18.09.6~3-0~ubuntu-bionic

# kubectl
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/v$KUBECTL_VERSION/bin/linux/amd64/kubectl \
  && chmod +x ./kubectl \
  && mv ./kubectl /usr/local/bin/kubectl \
  && kubectl version --client

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

# nodejs
RUN curl -sL https://deb.nodesource.com/setup_16.x | bash - \
  && apt-get -y install nodejs \
  && node --version

# internal artifactory artifacts
RUN python3.7 -m pip install tucla gnell uculegen

# extra tools
RUN wget https://github.com/mikefarah/yq/releases/download/v4.20.2/yq_linux_amd64 -O /usr/bin/yq \
  && chmod +x /usr/bin/yq
RUN apt install -y jq
RUN python3.7 -m pip install ansible

# OpenJDK 14
RUN add-apt-repository -y ppa:openjdk-r/ppa \
  && apt install -y openjdk-14-jdk openjdk-17-jdk \
  && update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-14-openjdk-amd64/bin/javac 1080 \
  && update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-14-openjdk-amd64/bin/java 1090 

# Gradle - latest
RUN apt install -y gradle \
  && update-alternatives --install /usr/bin/gradle gradle /usr/share/gradle/bin/gradle 100
  
# Maven 3.6.3
RUN  wget http://apache.mirrors.pair.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -O /tmp/apache-maven-3.6.3-bin.tar.gz \
  && tar xf /tmp/apache-maven-*.tar.gz -C /usr/share \
  && update-alternatives --install /usr/bin/mvn mvn /usr/share/apache-maven-3.6.3/bin/mvn 100

# Node 13.12.0
RUN wget https://nodejs.org/dist/v13.12.0/node-v13.12.0-linux-x64.tar.gz -O /tmp/node-v13.12.0-linux-x64.tar.gz \
  && tar xf /tmp/node-*.tar.gz -C /usr/share \
  && update-alternatives --install /usr/bin/nodejs nodejs /usr/share/node-v13.12.0-linux-x64/bin/node 20

RUN wget https://services.gradle.org/distributions/gradle-7.0.2-bin.zip  -O /tmp/gradle-7.0.2-bin.zip \
  && unzip /tmp/gradle-7.0.2-bin.zip -d /usr/share \
  && update-alternatives --install /usr/bin/gradle gradle /usr/share/gradle-7.0.2/bin/gradle 20

# sbt bootstrap
RUN mkdir -p /root/.sbt/1.0/plugins
COPY ./bootstrap-credentials.sbt /root/.sbt/1.0/bootstrap-credentials.sbt
COPY ./bootstrap-credentials.sbt /root/.sbt/1.0/plugins/bootstrap-credentials.sbt
COPY ./sbt-repositories.txt /root/.sbt/repositories
ENV SBT_CREDENTIALS=/root/.sbt/.credentials

# helmfile
RUN curl -fsSLO https://github.com/roboll/helmfile/releases/download/$HELMFILE_VERSION/helmfile_linux_amd64 \
    && mv ./helmfile_linux_amd64 /usr/local/bin/helmfile \
    && chmod +x /usr/local/bin/helmfile \
    && helmfile version

# nvm
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
