#!/bin/bash

# Example of build.sh
export APP_USER="user"
export APP_PASSWORD="user"

mvn clean compile exec:java
