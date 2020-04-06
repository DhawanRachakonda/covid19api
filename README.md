# COVID19 API 🏥

## Deploy

### Build the project

<code>mvn clean install</code>

### Deploy Prod

<code>java -Dspring.profiles.active=prod -jar target/demo-0.0.1-SNAPSHOT.jar</code>

# Docker 🎉

## Create a Sub Network

<code>docker network create slb-network</code>

## Inspect Sub Network

<code>docker network inspect slb-network</code>

## Spring Application

### Build

<code>sudo docker build -t covid19-api .</code>
<code>docker run --net slb-network -e "SPRING_PROFILES_ACTIVE=prod" -e "SPRING_APPLICATION_NAME=covid19-api-8080" -e "server.port=8080" --init --rm -d -p 8080:8080 --name covid19-api-8080 covid19-api</code>

## Nginx

<code>pwd <base_dir>/nginx</code>
<code>sudo docker build -t covid19-nginx .</code>
<code>sudo docker run --net slb-network --init --rm -d -p 80:80 -p 443:443 -v /etc/nginx/ssl:/etc/nginx/ssl covid19-nginx</code>

## List all process

<code>docker ps</code>

## Kill Docker Process

<code>docker kill <container_id></code>

## List all images

<code>docker image ls</code>
