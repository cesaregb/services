#!/bin/bash

# docker run -p 8080:8080 -e "APP_PROFILE=dev" -e "APP_PASSWORD=user" -e "DB_PASSWORD=Welcome1" -e "DB_USER=sod_user" -it interactivelabs/services:dev
# docker run -p 8080:8080 -e "APP_PROFILE=local" -e "APP_PASSWORD=user" -e "DB_PASSWORD=Welcome1" -e "DB_USER=root" -it interactivelabs/services:dev
docker run -p 8080:8080 -e "APP_PROFILE=docker" -e "APP_PASSWORD=user" -e "DB_PASSWORD=Welcome1" -e "DB_USER=sod_user" -it interactivelabs/services:dev
