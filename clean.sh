#!/bin/sh
docker stack rm dclou
docker images 'dcloudemo/*' --format '{{.ID}}' | xargs docker rmi
