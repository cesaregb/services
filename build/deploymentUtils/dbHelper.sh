#!/bin/bash

PGMNAME=`basename $0`

current_time=$(date "+%Y.%m.%d-%H.%M.%S")
cloud_database_url="soddb.ca6bb5j2bui8.us-east-1.rds.amazonaws.com:3306";


if [ $1 = "alter" ]; then
	echo "Running alter table..."

elif [ $1 = "backup" ]; then
	echo "Backing up cloud db"
	mysqldump database_name > /Users/cesaregb/dev/dumps/Cloud_${current_time}.sql
	ssh -i ~/.ssh/cesaregb.pem ec2-user@ec2-52-10-111-54.us-west-2.compute.amazonaws.com
else
	echo "Argument not valid"
	exit 1;
fi


exit 0;
