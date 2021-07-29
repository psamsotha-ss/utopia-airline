# Utopia Airline Project

Utopia Airlines is a fictitious airline. This project is a management system for this airline. The management system can be accessed by employees, admins, and travelers.

## Build

```shell
$ ./mvnw clean package
```

## Run

### Start MySQL on Docker

```shell
$ docker-compose up
```


## Info

### MySQL


Initialization scripts in ./mysql are copied during the MySQL docker container creation. They are run in alphabetical order, hence the naming with numbers during the Dockerfile COPY instruction. In order for these scripts to be run, the data volume needs to be empty. If you run docker-compose up multiple times, changes to the script will not take effect. To fix this, you can delete the docker volume either with docker volume prune or docker volume ls to list the volumes and then docker volume rm to remove individual volumes. These will be named xxx_mysql_data and xxx_mysql_config.