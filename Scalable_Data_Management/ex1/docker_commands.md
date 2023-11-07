## Basic docker commands 

- docker images (show images)
- docker ps (show all running containers)
- docker ps -a (list of containers that we ran)

### Delete a bunch of conatiners in one go
```sh
docker rm $(docker ps -a -q -f status=exited)
```

