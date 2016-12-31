#!/bin/bash

# Example of build.sh
export APP_USER="user"
export APP_PASSWORD="user"

# db.username=sod_user
# db.password=Welcome1


export DB_USER="root"
export DB_PASSWORD="Welcome1"

mvn clean compile exec:java
