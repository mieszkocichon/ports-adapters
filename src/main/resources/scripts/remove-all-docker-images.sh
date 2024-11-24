docker ps -q | xargs docker stop
docker ps -aq | xargs docker rm
docker images -q | xargs docker rmi -f
docker image prune -a --force
docker system prune -a --volumes --force