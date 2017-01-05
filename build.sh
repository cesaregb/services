#!/bin/bash

# Example of build.sh

# Local information...
export APP_USER=user
export APP_PASSWORD=user
export DB_USER=root
export DB_PASSWORD=Welcome1
export APP_PROFILE=local

cd project

mvn clean compile exec:java
