#!/bin/sh

HOST="$1"

mvn -s .travis.settings.xml -B package -DdeployDocker -DskipTests

ssh user@$HOST 'ssh user@10.10.50.11 rm -rf demogpb'
ssh user@$HOST 'ssh user@10.10.50.11 git clone https://github.com/dclou/demogpb'
ssh user@$HOST 'ssh user@10.10.50.11 "cd demogpb && ./run.sh"'
