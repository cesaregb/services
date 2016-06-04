# To build:
# docker build -t interactivelabs/services:v1 -f Dockerfile .
#
# To publish:
  # docker logn
  # docker push interactivelabs/services
# docker run -p 8080:8080 -it interactivelabs/services
# docker run -p 8080:8080 -d interactivelabs/services
# docker run -p 8080:8080 -it  --entrypoint bash interactivelabs/services
#
# To run:
# docker run -p 8080:8080 -it interactivelabs/services:v1
FROM centos:6
MAINTAINER cesareg.borjon@gmail.com

#install java
ENV JAVA_VERSION 8u31
ENV BUILD_VERSION b13

# Upgrading system
RUN yum -y upgrade
RUN yum -y install wget
#RUN apt-get update

RUN yum -y install git curl unzip tar

# Downloading Java
RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/$JAVA_VERSION-$BUILD_VERSION/jdk-$JAVA_VERSION-linux-x64.rpm" -O /tmp/jdk-8-linux-x64.rpm
RUN yum -y install /tmp/jdk-8-linux-x64.rpm

RUN alternatives --install /usr/bin/java jar /usr/java/latest/bin/java 200000
RUN alternatives --install /usr/bin/javaws javaws /usr/java/latest/bin/javaws 200000
RUN alternatives --install /usr/bin/javac javac /usr/java/latest/bin/javac 200000

ENV JAVA_HOME /usr/java/latest

# Install maven
RUN wget "http://www.us.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz"
RUN tar xzf apache-maven-3.3.9-bin.tar.gz -C /usr/local
WORKDIR /usr/local
RUN ln -s apache-maven-3.3.9 maven

ENV PATH=$PATH:/usr/local/maven/bin

# Install
RUN cd /tmp && wget http://nodejs.org/dist/v0.12.3/node-v0.12.3-linux-x64.tar.gz
RUN cd /usr && tar --strip-components 1 -xzf /tmp/node-v0.12.3-linux-x64.tar.gz

#User IL = Interactive labs
ENV BASE_USER il
ENV USER_HOME /home/$BASE_USER

# Create our user
RUN useradd $BASE_USER

#make the base_user the owner of all its HOME files, including the .ssh and DOCENG_BASE_SRC dirs
RUN chown -R $BASE_USER:$BASE_USER $USER_HOME


#docker project file specific
EXPOSE 8080
#microservice name
ENV MODULE services
ENV MODULE_SOURCE ${USER_HOME}/${MODULE}
# add in the source files
RUN mkdir -p ${MODULE_SOURCE}
COPY /project ${MODULE_SOURCE}/project

# make doceng the owner of all the source files, so when mvn clean install package runs it can create the target/classes dir
RUN chown -R ${BASE_USER}:${BASE_USER} ${MODULE_SOURCE}

USER $BASE_USER

# Build the registration services now
WORKDIR ${MODULE_SOURCE}/project
RUN mvn clean install package

#When this image is run as a container, start the jetty server. It will be listening on the ${REGISTRATION_API_PORT}
# ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "target/sod_project-1.0.jar"]
# ENTRYPOINT ["mvn", "clean", "compile", "-Dspring.profiles.active=dev", "exec:java"]
ENTRYPOINT ["mvn", "clean", "compile", "-Dspring.profiles.active=dev", "exec:java", "-Dhttps.protocols=TLSv1,SSLv3"]
