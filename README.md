# Docker for development environments

Currently, Docker is the most difussed container technology. It's possible you are thinking about kubernetes and the decision about don't use Docker as a Runtime.
Well, it's true. But Docker will be been compatible with K8.

So, we can continue using Docker for another things like Development.

# Docker compose

Docker-compose is a technology for running multi container docker applications. You can use it for deploy stacks in any place (usually a server).
But this is useful for helping in the development stuff.

# Basic compose commands

```bash
# Start an stack
docker-compose up
# Start a stack in bg
docker-compose up -d
# Stop and delete containers
docker-compose down
# Run some command in a service (starting the service)
docker-compose run --rm container-name command
# If the service is up. Run in an existing container
docker-compose exec container-name command
```

# Basic structure of a compose file (based on docker compose [overview ](https://docs.docker.com/compose/))

```yaml
version: "3.9"  # optional since v1.27.0
services:   # Block for services definition
  web:
    build: .      # If the image use some Dockerfile to create the image
    ports:
      - "5000:5000"          # Port mapping section host:container
    volumes:
      - .:/code       # If you wanna use some map some host folder in the container
  redis:
    image: redis      # Another service
```
