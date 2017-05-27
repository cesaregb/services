## Java Web RESTful app
XML-less Spring + Jersey + Swagger + Hibernate(JPA)

This application contains a basic set up of a REST services with Jersey integrated with Spring and Hibernate. 
Configuration for handling security in the requests. 
Hibernate (JPA) integrated with Spring.  

### Docker image integration 



Project Structure
---------
```
.
├── Dockerfile
├── README.md
├── buil.sh
├── docker
│   ├── Dockerfile.app
│   ├── Dockerfile.db
│   ├── Dockerfile_jar.app
│   └── setup.sql
├── dockerImage.sh
├── java_project
│   ├── log
│   ├── pom.xml
│   ├── rollFile.log
│   ├── sod_project.iml
│   ├── src
│   └── target
├── logstash.conf
├── secure
└── sod_general
    ├── database
    ├── deploymentUtils
    ├── info.txt
    ├── payloads
    └── queries
	
```

The Java project is within the project folder (java_project).
	
### Compiling
	mvn clean install 

### Running in Jetty
	mvn clean compile exec:java

### Helper script
    ./build.sh

Then open <http://localhost:8080/> 

