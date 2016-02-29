cd db-postgresql
docker build -t metapro-api-db .
docker run -d -p 5432:5432 --name metapro-db metapro-api-db
cd ..
cd metapro-api-server
docker build -t metapro-api-app .
docker run -d -p 8080:8080 --name metapro-api-app metapro-api-app

HOW TO STOP AND DELETE metapro-api-app CONTAINER:
docker stop metapro-api-app
docker rm metapro-api-app


HOW TO GET ATTACHED TO A RUNNING CONTAINER: sudo docker exec -i -t metapro-api-app bash
