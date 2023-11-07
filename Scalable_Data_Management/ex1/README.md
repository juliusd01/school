# Postgres application in docker

- Created user-defined bridge network called "test-net"
- pulled postgres image from DockerHub

Start the postgres-db container by calling:
```sh
docker run --rm --network test-net --name postgres-db -e POSTGRES_PASSWORD=mysecretpassword postgres
```

Then open new shell and start new container for client by calling:
```sh
docker run --name postgres-client --rm -t -i --network test-net postgres bash
```

To start the command-line interface for PostgreSQL type:
```sh
psql -h postgres-db -p 5432 -U postgres
```

Finally, enter password 'mysecretpassword' and run sql commands.
