# Run SQL commands in docker container

### start container
```sh
docker run --name some-postgres -v /postgres:/var/lib/postgresql/data -e POSTGRES_PASSWORD=mysecretpassword -d postgres:14
```

### run sql commands
```sh
docker exec -ti some-postgres psql -U postgres
```

## Q1
```sql
select customer.acctbal, customer.custkey, customer.mktsegment from customer where customer.mktsegment='FURNITURE' order by customer.acctbal DESC
```

## Q2
```sql
select customer.acctbal, customer.custkey, customer.mktsegment, nation.name  from customer, nation where customer.mktsegment='FURNITURE' AND customer.nationkey=nation.nationkey order by customer.acctbal DESC
```