Java Web RESTful app
XML-less Spring + Jersey + Swagger + Hibernate(JPA) 
Docker image integration 
========================

This application contains a basic set up of a REST services with Jersey integrated with Spring and Hibernate. 
Configuration for handling security in the requests. 
Hibernate (JPA) integrated with Spring.  

Project
---------
.
├── build
│   └── payloads
├── docker
└── project
    ├── src
    └── target
	
The Java project is within the project folder. 
	
Compiling
---------
	mvn clean install

Running in Jetty
----------------
	mvn clean compile exec:java

Then open <http://localhost:8080/> 

