#!/usr/bin/env bash

DB_IMAGE_NAME=database
BUILD_VERSION=V1
MYSQL_PORT_3306_TCP_ADDR=3306
MYSQL_USER=application
MYSQL_DATABASE=jpa_example_shops
MYSQL_PASSWORD=pasSwOrd#12
MYSQL_ROOT_PASSWORD=Welcome#1

#copy the sql file
cp ../build/jpa_example_shops.sql setup.sql
chmod 755 setup.sql


DBDATA=`docker images -q database`
if [[ -z $DBDATA ]]
then
    echo "**SC - creating new shared volume called 'dbdata'"
    # Reference: https://docs.docker.com/userguide/dockervolumes/#creating-and-mounting-a-data-volume-container
    # docker create -v /dbdata --name dbdata mysql:5.7 /bin/true

    echo "**SC - building image database:v1"
    docker build -t ${DB_IMAGE_NAME}:${BUILD_VERSION} -f Dockerfile.db .

fi



# The following is set up to be ephemeral, so that the instances that you
# launch are cleaned up after running. Uses the shared Docker volume, dbdata.
# As a note, we modified the entrypoint script so that it pulls in a sql script
# that runs against our existing database every time we run. While this is fine
# for an example, it shouldn't be done in production, as it might blow something up.
# Reference: https://docs.docker.com/userguide/dockervolumes/#creating-and-mounting-a-data-volume-container
# Run command shown here: https://coreos.com/products/enterprise-registry/docs/latest/mysql-container.html
echo "**SC - running image!"

COMMAND="docker run -d -p 3306:3306 --env MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD} --env MYSQL_USER=${MYSQL_USER} --env MYSQL_PASSWORD=${MYSQL_PASSWORD} --env MYSQL_DATABASE=${MYSQL_DATABASE} --volumes-from dbdata ${DB_IMAGE_NAME}:${BUILD_VERSION}"

echo "cmd: ${COMMAND} "

ID=$(docker run \
    -d -p 3306:3306 \
    --env MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD} \
    --env MYSQL_USER=${MYSQL_USER} \
    --env MYSQL_PASSWORD=${MYSQL_PASSWORD} \
    --env MYSQL_DATABASE=${MYSQL_DATABASE} \
    --volumes-from dbdata \
    ${DB_IMAGE_NAME}:${BUILD_VERSION};)

echo "ID: ${ID}"

# docker run --rm database:v1

# Kill the ephemeral mysql daemon image
# docker stop $ID
# docker rm -f $ID
