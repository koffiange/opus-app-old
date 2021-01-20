#!/bin/sh
if [ $(docker ps -a -f name=pole-opus | grep -w pole-opus | wc -l) -eq 1 ]; then
  docker rm -f pole-opus
fi
mvn clean package && docker build -t ci.siracide/pole-opus .
docker run -d -p 9080:9080 -p 9443:9443 --name pole-opus ci.siracide/pole-opus
