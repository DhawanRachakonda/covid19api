# COVID19 API 🏥

## Deploy

### Build the project

<code>mvn clean install</code>

### Deploy Prod

<code>java -Dspring.profiles.active=prod -jar target/demo-0.0.1-SNAPSHOT.jar</code>

## Docker 🎉

### Build

<code>sudo docker build -t covid19-api .</code>
<code>docker run -e "SPRING_PROFILES_ACTIVE=prod" --init --rm -d -p 8080:8080 covid19-api</code>

### Kill Docker Process

<code>docker ps <container_id></code>

### List all images

<code>docker image ls</code>