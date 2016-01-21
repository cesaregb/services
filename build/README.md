#Registration Services
This set of REST services allows for publications to be registered for Self Publishing. It also provides services for retrieving attributes of a given environment (e.g. the front-end hostname for UAT).

##To Build
The Docker image for the Registration Services can be built using the supplied Dockerfile. From the root of the repo:
    docker build -t registration-services -f docker/Dockerfile --build-arg GIT_BRANCH=<tag-name> .

##To Run
Once the image has been built, the container can be run, but be sure to expose the port in the docker host, e.g.:
    docker run -p 9999:9999 registration-services

#Release Notes
##0.0.3 Updates
- Fixed bug in 0.0.2 where mvn command failed in building the registration project. Some previously locally referenced dependencies had been relocated (i.e. reference was expecting them to be in Maven repo).

##0.0.2 Updates
- Using new doceng-libs-release-local Artifactory repo for required JDK and Maven binaries instead of old tesseract-local repo.
