# Featr

A viewer and REST-API for Feature Files. You can get an overview if the  directory structure does not.


# Build instructions


## Build
mvn clean package
mvn install -Dmaven.test.skip=true

### Or at once
mvn clean package install -Dmaven.test.skip=true

## Run the container
docker run -d -p 8084:8084 -v /repos/Dictionary:/repos -v /Users/arjan/Projects/Feature/application.yml:/application.yml --name featr-server featr-server:latest

## Open a bash-terminal on the container
docker exec -t -i featr-server /bin/bash

## Stop the container
docker stop featr-server && docker rm featr-server
