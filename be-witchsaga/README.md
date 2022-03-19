### Environment
PostgresSQL, Java 1.8 or Higher, Kubernetes, Docker

## How to Run Apps
### Prerequisites
1. Docker and Kubernetes
2. Java 1.8 or higher

### Step 1 : Build Spring Boot Application
Run a command using command prompt or shell in this current directory.
```shell
$ ./gradlew build
```
### Step 2 : Build Docker Image from .jar
Make sure **docker already installed** and check using this command.
```shell
$ docker version
```
Build a docker image from DockerFile.

(It's build script to deploy your app in docker container)
```shell
$ docker build -t . localhost:5000/witch-saga-app:0.0.1-SNAPSHOT
```
### Step 3 : Push Docker Image into Docker Registry
Run docker registry in this local machine.
```shell
$ docker run -d -p 5000:5000 --restart=always --name registry registry:2 
```
Check if docker registry already running.
```shell
$ docker ps
```
Push docker image into docker registry
```shell
$ docker push localhost:5000/witch-saga-app:0.0.1-SNAPSHOT
```
Remove docker image from the local cache.<br>
Try pull the docker image to make sure if already registered
```shell
$ docker pull localhost:5000/witch-saga-app:0.0.1-SNAPSHOT
```
### Step 4 : Deployment Configuration in Kubernetes Cluster
Make sure **kubernetes already installed** and check using this command.
```shell
$ kubectl version
```
Navigate to deployment directory under this directory
```shell
$ cd .\deployment\
```
Run this command to apply the configurations
```shell
$ kubectl create namespace witch-saga
$ kubectl apply -f .\postgresql-service.yaml -f .\postgresql-statefulset.yaml --namespace=witch-saga
$ kubectl apply -f .\deployment.yaml -f .\service.yaml --namespace=witch-saga
```
Check if pod is already running
```shell
$ kubectl get pod -n witch-saga
```
### Step 5 : Forwarding Port from the Local Machine to the Pod Container
```shell
$ kubectl port-forward -n witch-saga pod/witch-saga-6d5b6c5b85-p4klc 8081:80
```
### Step 6 : Access using Browser in this URL
http://localhost:8081/movie-service/swagger-ui/