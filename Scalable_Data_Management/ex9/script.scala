// Task 1
// Reading data into RDD
val spark = org.apache.spark.sql.SparkSession.builder.master("local").appName("SparkShell").getOrCreate()
val sc = spark.sparkContext

val file = sc.textFile("items.dat")
val records = file.map(line => line.split("::"))