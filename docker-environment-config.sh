#!/bin/bash
# @author Juliano Almeida da Silva
# Arquivo responsável por executar a aplicação no ambiente local utilizando docker
echo "*************************************************"
echo "Checking Java version"
echo "*************************************************"
if which java; then
    echo "Found java executable in PATH"
    _JAVA=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo "Found java executable in JAVA_HOME"
    _JAVA="$JAVA_HOME/bin/java"
else
    echo "Java was not found. Install Java 11, you may use jabba for this (https://github.com/shyiko/jabba)"
    exit 1
fi

if [[ "$_JAVA" ]]; then
    version=$("$_JAVA" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "Java version $version"
    version=$(echo "$version"| cut -d'.' -f 1)
    if [[ "$version" -ge "17" ]]; then
        echo "Version $version is more or equal than 17"
    else
        echo "Version $version is less than 17"
        exit 1
    fi
fi
echo "*************************************************"
echo "Building..."
echo "*************************************************"
./gradlew clean
./gradlew bootDistZip
echo "*************************************************"
echo "Copying files to create local docker image..."
cp challenge-api/build/distributions/*.zip docker/
cp challenge-api/DockerfileApi docker/
cp challenge-consumer/build/distributions/*.zip docker/
cp challenge-consumer/DockerfileConsumer docker/
echo "*************************************************"
echo "Removing old docker image and containers related to challenge_customer..."
echo "*************************************************"
docker ps -a | awk '{ print $1,$2 }' | grep local.docker/challenge_customer_api | awk '{print $1 }' | xargs -I {} docker container rm -f {}
docker ps -a | awk '{ print $1,$2 }' | grep local.docker/challenge_customer_consumer | awk '{print $1 }' | xargs -I {} docker container rm -f {}
docker image rm local.docker/challenge_customer_api
docker image rm local.docker/challenge_customer_consumer
echo "*************************************************"
echo "Creating docker image..."
echo "*************************************************"
# shellcheck disable=SC2164
cd docker
docker build -t local.docker/challenge_customer_api -f DockerfileApi .
docker build -t local.docker/challenge_customer_consumer -f DockerfileConsumer .
echo "*************************************************"
echo "Removing generated files..."
rm docker/challenge-api*.zip
rm docker/challenge-consumer*.zip
echo "*************************************************"
echo "Entering the folder to execute the application..."
# shellcheck disable=SC2103
cd ..
echo "*************************************************"
echo "Executing the application..."
echo "*************************************************"
docker-compose up
./gradlew flywayMigrate
echo "*************************************************"