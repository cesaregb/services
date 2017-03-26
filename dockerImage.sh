#!/bin/bash

if [ "${1}" = "build" ]
then
  docker build -t interactivelabs/services:dev -f Dockerfile .
else
  docker run -p 8080:8080 -e "APP_PROFILE=local" -e "APP_PASSWORD=user" -e "DB_PASSWORD=Welcome1" -e "DB_USER=root" \
    -it interactivelabs/services:dev
fi
