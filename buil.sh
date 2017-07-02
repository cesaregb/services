#!/bin/bash

# Example of build.sh

# Local information...
# print help

export APP_USER=user
export APP_PASSWORD=user
export APP_PROFILE="local"
export DB_URL="jdbc:mysql://127.0.0.1:3306/sod_db"
export DB_USER=root
export DB_PASSWORD=Welcome1

function exportProd(){
    echo "Loading prod variables..."
    propertiesFile=".env"
    echo "{build.sh} Sourcing file ${propertiesFile} ..."
    cat ${propertiesFile} | sed '/^$/! s/^/export /' > export.properties
    source export.properties
    echo "{build.sh} ********** export.properties [init]"
    cat export.properties
    echo "{build.sh} ********** export.properties [end]"
}

if [[ ${1} == "prod" ]]; then
    exportProd
fi

# Logging
  # ALL > TRACE > DEBUG > INFO > WARN > ERROR > FATAL > OFF
export soutLevelVal="ALL"
# 1 = all; 2 = sql + app; 3 = app
export stoutType="3"

cd java_project
mvn clean compile exec:java
