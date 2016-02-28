docker build -t metpro-api-db .
docker run -d -p 5432:5432 --name metapro-db metapro-api-db
