#!/bin/sh

docker run -i -t \
       -p 4040:4040 \
       -p 8080:8080 \
       -p 8081:8081 \
       -v "${PWD}":/root/exercise \
       -h spark \
       --name=spark \
       p7hb/docker-spark
