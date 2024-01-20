// Part 2 deals with dataframes from spark instead of RDD's

//TASK 5
//Read files into data frames
val movie_df = spark.read.text("input/items.dat")
  .withColumn("value", split(col("value"), "::"))
  .select(
    col("value").getItem(0).as("movie_id"),
    col("value").getItem(1).as("movie_title"),
    col("value").getItem(2).as("genre")
  )
  .withColumn("year", regexp_extract(col("movie_title"), "\\((\\d{4})\\)", 1))
  .withColumn("movie_title", regexp_replace(col("movie_title"), "\\s*\\(\\d{4}\\)\\s*", ""))

val ratings_df = spark.read.text("input/ratings.dat")
.withColumn("value", split(col("value"), "::"))
.select(col("value").getItem(0).as("userid"),
        col("value").getItem(1).as("movieid"),
        col("value").getItem(2).as("rating"),
        col("value").getItem(3).as("time"))

// TASK 6
// Return number of users with 20+ ratings
val num_users = ratings_df.select("userid").distinct().count()
val users_with_20_ratings = ratings_df.groupBy("userid").count().filter("count >= 20").count()

// Return movie with best average rating and at least 5 ratings
val movie_avg_rating = ratings_df.groupBy("movieid").agg(avg("rating").as("avg_rating"))
val movie_with_5_ratings = ratings_df.groupBy("movieid").count().filter("count >= 5")
val best_movie = movie_avg_rating.join(movie_with_5_ratings, "movieid").orderBy(desc("avg_rating")).first()


// TASK 7
// Register the DataFrames as tables
movie_df.createOrReplaceTempView("movies")
ratings_df.createOrReplaceTempView("ratings")

// Return number of users with 20+ ratings with SQL
val numUsers20RatingsSQL = spark.sql("SELECT COUNT(DISTINCT userid) FROM ratings WHERE userid IN (SELECT userid FROM ratings GROUP BY userid HAVING COUNT(*) >= 20)")
numUsers20RatingsSQL.show()


// TASK 8
// Read the .dat file into a DataFrame
val df = spark.read.text("input/items.dat")

// Split the "value" column into multiple columns
val movie_df2 = df.select(
  split(col("value"), "::").getItem(0).cast(IntegerType).as("movie_id"),
  split(col("value"), "::").getItem(1).as("movie_title"),
  split(col("value"), "::").getItem(2).as("genre"),
  regexp_extract(col("value"), "\\((\\d{4})\\)", 1).cast(IntegerType).as("year")
)

// Define a case class that matches the structure of your data
case class Movie(movie_id: Int, movie_title: String, genre: String, year: Int)

// Convert the DataFrame to a Dataset
val moviesDS = movie_df2.as[Movie]

// Return number of movies per decade (use groupByKey and mapGroups)
val moviesPerYear = moviesDS.groupByKey(_.year).mapGroups {
  case (year, movies) => (year, movies.size)
}	

val moviesPerDecade = moviesPerYear.groupByKey(_._1 / 10 * 10).mapGroups {
  case (decade, movies) => (decade, movies.map(_._2).sum)
}

val moviesPerDecadeS = moviesPerDecade.orderBy(asc("_1"))