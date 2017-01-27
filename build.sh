#!/bin/bash

# Example of build.sh

# Local information...
export APP_USER=user
export APP_PASSWORD=user
export APP_PROFILE="local"
export DB_URL="jdbc:mysql://127.0.0.1:3306/sod_db"
export DB_USER=root
export DB_PASSWORD=Welcome1

cd project

mvn clean compile exec:java
