# Excercise 3: Redis 🍤

## Connect to redis Server
```sh
docker run --rm --network redis-network --name myredis redis
```

## Connect to redis Client
```sh
docker run --rm -it --network redis-network redis redis-cli -h myredis
```

## T1: add customers
```
SET customer:1 '{"custkey":1,"name":"Customer#000000001","address":"IVhzIApeRb ot,c,E","nationkey":15,"phone":"25-989-741-2988","acctbal":711.56,"mktsegment":"BUILDING","comment":"to the even, regular platelets. regular, ironic epitaphs nag e"}'
```
```
SET customer:2 '{"custkey":2,"name":"Customer#000000002","address":"XSTf4,NCwDVaWNe6tEgvwfmRchLXak","nationkey":13,"phone":"23-768-687-3665","acctbal":121.65,"mktsegment":"AUTOMOBILE","comment":"l accounts. blithely ironic theodolites integrate boldly: caref"}'
```
```
SET customer:3 '{"custkey":3,"name":"Customer#000000003","address":"MG9kdTD2WBHm","nationkey":1,"phone":"11-719-748-3364","acctbal":7498.12,"mktsegment":"AUTOMOBILE","comment":"deposits eat slyly ironic, even instructions. express foxes detect slyly. blithely even accounts abov"}'
```

## T2: Store whole relation of table Customer in Redis
- Data Structure: dictionary (customer key as key and a dictionary for values)
