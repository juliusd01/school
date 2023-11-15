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
another option is to use an inner join:
```sql
select customer.acctbal, customer.custkey, customer.mktsegment, nation.name  from customer inner join nation on customer.nationkey = nation.nationkey where customer.mktsegment = 'FURNITURE' order by customer.acctbal desc;
```

## Q3
```sql
select region.name, SUM(customer.acctbal)  from customer inner join nation on customer.nationkey = nation.nationkey inner join region on region.regionkey = nation.regionkey where customer.mktsegment = 'FURNITURE' group by region.name order by SUM(customer.acctbal) desc
```

## Q4
```sql
select lineitem.orderkey, SUM(lineitem.tax*lineitem.quantity*(lineitem.extendedprice - lineitem.discount)) as total_taxes_paid from lineitem inner join orders on orders.orderkey = lineitem.orderkey group by lineitem.orderkey
```

## Q5 
```sql
select part.name as part_name, supplier.name as supplier_name, part.partkey, SUM(partsupp.availqty) from part inner join partsupp on part.partkey = partsupp.partkey inner join supplier on supplier.suppkey = partsupp.suppkey group by (part.partkey, part.name, supplier.name)
```

## Q6
```sql
select part.name as part_name, part.partkey, SUM(partsupp.availqty) as sum_quantity from part inner join partsupp on part.partkey = partsupp.partkey group by (part.partkey, part.name) having SUM(partsupp.availqty) >= 25000
```