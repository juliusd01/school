spark-shell \
    --master local[*] \
    --driver-memory 2G \
    --conf spark.sql.inMemoryColumnarStorage.compressed=true \
    --conf spark.sql.shuffle.partitions=4 
