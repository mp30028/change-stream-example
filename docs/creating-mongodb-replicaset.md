1 - Create the docker network

	`docker network create mongoNetwork`

2 - Start the mongodb instances

`docker run -d --rm -p 27021:27017 --name mongo1 --network mongoNetwork mongo:6.0.2 mongod --replSet dockedReplicaSet --bind_ip localhost,mongo1`

`-d` indicates that this container should run in detached mode (in the background).

`-p` indicates the port mapping. Any incoming request on port 27018 on your machine will be redirected to port 27018 in the container.

`--name` indicates the name of the container. This will become the hostname of this machine.

`--network` indicates which Docker network to use. All containers in the same network can see each other.

`mongo:6.0.2` is the image that will be used by Docker. This image is the MongoDB Community server version (maintained by Docker). You could also use a MongoDB Enterprise custom image.



3 - Start the other two containers


```
docker run -d --rm -p 27022:27017 --name mongo2 --network mongoNetwork mongo:6.0.2 mongod --replSet dockedReplicaSet --bind_ip localhost,mongo2

docker run -d --rm -p 27023:27017 --name mongo3 --network mongoNetwork mongo:6.0.2 mongod --replSet dockedReplicaSet --bind_ip localhost,mongo3
```

4 - Start an interactive unix shell

`docker exec -it mongo1 bash`


5 - Start a mongodb shell from the bash command line

`mongosh`


6 - Start cluster

`rs.initiate({_id: "dockedReplicaSet", members: [{_id: 0,  host: "mongo1"}, {_id: 1, host: "mongo2"}, {_id: 2, host: "mongo3"}]})`


7 - Check the cluster is running ok

`rs.status()`

8 - In the `applications.properties` file changed `spring.data.mongodb.uri=mongodb://localhost:27021/changestream`

9 - Got some errors about not finding hosts mongo1 etc. So updated `C:\Windows\System32\drivers\etc\hosts` file and added the following

```
127.0.0.1       localhost
::1             localhost
127.0.0.1 	mongo1
127.0.0.1 	mongo2
127.0.0.1 	mongo3
```


10 - Start Mongo Cluster using docker-compose based on files in `replicaset-docker-compose.zip`

11 - Initialise the cluster

-----   11.1 - Shell into the docker container `docker exec -it mongo1 bash`

-----   11.2 - Change to scripts directory `cd scripts` 

-----   11.3 - Run the rs-init.sh script `sh ./rs-init.sh`

-----   11.4 - Check the status `rs.status()`

12 - Update the connection string so that the port number matches the primary mongo node in the cluster

10 - For full instructions see this link
https://www.mongodb.com/compatibility/deploying-a-mongodb-cluster-with-docker