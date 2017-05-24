#!/bin/sh
docker-machine create -d virtualbox --virtualbox-cpu-count 4 --virtualbox-memory 7096 dclou || true
eval $(docker-machine env dclou)
docker stack deploy -c docker-compose.yml dclou
# List all images
docker stack ps dclou
#Overall service info
docker service ls