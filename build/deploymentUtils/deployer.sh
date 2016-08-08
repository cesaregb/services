#!/bin/bash

PGMNAME=`basename $0`

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

# clean docker
docker volume rm $(docker volume ls -qf dangling=true)
docker rm -v $(docker ps -aq)
docker rmi $(docker images --quiet --filter "dangling=true")


if [ $1 = "deploy_services" ]; then
	echo "Deploying interactivelabs/services"
	IMAGE="interactivelabs/services"
	docker ps | grep $IMAGE | awk '{print $1}' | xargs docker stop
	docker pull $IMAGE
	if [ $2 = "dev" ]; then
		docker run -p 8080:8080 -e "APP_PROFILE=dev" -d $IMAGE
	else
		docker run -p 8080:8080 -e "APP_PROFILE=prod" -d $IMAGE
	fi
elif [ $1 = "deploy_web" ]; then
	echo "Deploying interactivelabs/laundry-web"
	IMAGE="interactivelabs/laundry-web"
	docker ps | grep $IMAGE | awk '{print $1}' | xargs docker stop
	docker pull $IMAGE
	docker run -p 3000:3000 -d $IMAGE
elif [ $1 = "deploy_admin" ]; then
	echo "Deploying interactivelabs/process-admin"
	IMAGE="interactivelabs/process-admin"
	# this may fail but required to start it again...
	docker ps | grep mongo | awk '{print $1}' | xargs docker stop
	docker rm mongo-admin
	docker run -p 27017:27017 --name mongo-admin -d mongo
	docker ps | grep $IMAGE | awk '{print $1}' | xargs docker stop
	docker pull $IMAGE
	docker run -p 9000:9000 --link mongo-admin:mongo -d $IMAGE
else
	echo "Argument not valid [deploy_services || deploy_web || deploy_admin]"
	exit 1;
fi

exit 0;
