#!/bin/bash

PGMNAME=`basename $0`

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

if [ $1 = "deploy_services" ]; then
	echo "Deploying interactivelabs/services"
	IMAGE="interactivelabs/services"
	docker ps | grep $IMAGE | awk '{print $1}' | xargs docker stop
	# docker pull $IMAGE
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
	docker run -p 8080:8080 -d $IMAGE
elif [ $1 = "deploy_admin" ]; then
	echo "Deploying interactivelabs/process-admin"
	IMAGE="interactivelabs/process-admin"
	docker ps | grep $IMAGE | awk '{print $1}' | xargs docker stop
	docker pull $IMAGE
	docker run -p 8080:8080 -d $IMAGE
else
	echo "Argument not valid"
	exit 1;
fi

exit 0;


# how to run this with docker
# docker pull bketelsen/captainhook
# mkdir /some/local/config
# $EDITOR /some/local/config/myhook.json
# docker run -d -v /some/local/config:/config bketelsen/captainhook
