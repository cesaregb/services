# To build:
# docker build -t interactivelabs/services:dev -f Dockerfile .
#
# To publish:
  # docker logn
  # docker push interactivelabs/services
#
# To run:
# docker run -p 8080:8080 -e "APP_PROFILE=dev" -it interactivelabs/services
# docker run -p 8080:8080 -e "spring.profiles.active=dev" -d interactivelabs/services
# docker run -p 8080:8080 -e "APP_PROFILE=dev" -it --entrypoint bash interactivelabs/services
FROM maven:3-alpine
MAINTAINER cesareg.borjon@gmail.com

#docker java_project file specific
EXPOSE 8080

#microservice name
ENV MODULE services
ENV MODULE_SOURCE ${USER_HOME}/${MODULE}
# add in the source files
RUN mkdir -p ${MODULE_SOURCE}
COPY /java_project ${MODULE_SOURCE}/java_project

# Build the registration services now
WORKDIR ${MODULE_SOURCE}/java_project
RUN mvn clean install package

ENTRYPOINT mvn compile exec:java
