#To build:
# docker build -t registration-services-prod -f docker/Dockerfile --build-arg GIT_BRANCH=1.0.2 .
#
# To run:
# docker run -e swagger-host=$(hostname -f) -p 8080:8080 -it registration-services-dev
FROM doceng.docker.oraclecorp.com/doceng-base:0.0.7
MAINTAINER jesse.stephens@oracle.com

ARG GIT_BRANCH

ENV MODULE registration-services
ENV MODULE_SOURCE ${USER_HOME}/${MODULE}
# 8080 is the port the jetty server is currently set to listen
ENV REGISTRATION_API_PORT 8080
ENV MODULE_VERSION $GIT_BRANCH

# use -P/-p at run time to expose this port from the Docker host
EXPOSE $REGISTRATION_API_PORT

# Maven needs JAVA_HOME set to a JDK home
ENV JAVA_HOME /usr/java/jdk1.8.0_66

RUN cd /tmp && \
    wget "http://artifactory-slc.oraclecorp.com/artifactory/doceng-libs-release-local/oracle/jdk/8u66-b17/jdk-8u66-linux-x64.rpm" && \
    yum localinstall -y /tmp/jdk-8u66-linux-x64.rpm && \
    rm /tmp/jdk-8u66-linux-x64.rpm

# Install Maven
RUN cd /tmp && \
    wget "http://artifactory-slc.oraclecorp.com/artifactory/doceng-libs-release-local/apache/maven/3.3.9/apache-maven-3.3.9-bin.tar.gz" && \
    cd /opt && \
    tar xzvf /tmp/apache-maven-3.3.9-bin.tar.gz && \
    rm /tmp/apache-maven-3.3.9-bin.tar.gz

# add mvn command to PATH
ENV PATH=$PATH:/opt/apache-maven-3.3.9/bin

# add in the source files
RUN mkdir -p ${MODULE_SOURCE}
RUN git clone --branch $GIT_BRANCH --depth 1 alm:/doceng_selfpublish/${MODULE}.git ${MODULE_SOURCE}

# make doceng the owner of all the source files, so when mvn clean install package runs it can create the target/classes dir
RUN chown -R ${BASE_USER}:${BASE_USER} ${MODULE_SOURCE}

USER $BASE_USER

# settings.xml sets the proxy for Maven so it can download its dependencies
RUN mkdir ~/.m2 && cp ${MODULE_SOURCE}/build/maven/settings.xml ~/.m2/

# Build the registrationModel project first, and it will go into the local Maven repo
# and be available to resolve the depedency by the registration/pom.xml
WORKDIR ${MODULE_SOURCE}/registrationModel
RUN mvn clean install package

# Build the registration services now
WORKDIR ${MODULE_SOURCE}/registration
RUN mvn clean install package shade:shade -Pdev

#When this image is run as a container, start the jetty server. It will be listening on the ${REGISTRATION_API_PORT}
ENTRYPOINT ["java", "-jar", "target/registration.jar"]
