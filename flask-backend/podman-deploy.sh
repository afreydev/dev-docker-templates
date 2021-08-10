#!/bin/bash
./Buildahfile.sh
PODMAN_NETWORK="companies_network"
DB_CONTAINER_NAME="companies_mariadb"
FLASK_CONTAINER_NAME="companies"

podman network create "$PODMAN_NETWORK"
podman run -d --restart=always --env-file=.env --network="$PODMAN_NETWORK" --name="$DB_CONTAINER_NAME" mariadb
DB_HOST=$(podman inspect --format "{{ .NetworkSettings.Networks.$PODMAN_NETWORK.IPAddress }}" $DB_CONTAINER_NAME)
sed -i "s/MYSQL_HOST.*/MYSQL_HOST=$DB_HOST/" .env
podman run -d --restart=always --env-file=.env -p 8080:5000 --network="$PODMAN_NETWORK" --name="$FLASK_CONTAINER_NAME" localhost/flask-backend:latest
