// TASK 1
// Reading data into RDDS
val spark = org.apache.spark.sql.SparkSession.builder.master("local").appName("SparkShell").getOrCreate()
val sc = spark.sparkContext

val movies = sc.textFile("input/items.dat")
val ratings = sc.textFile("input/ratings.dat")

val moviesRecords = movies.map(line => line.split("::"))
val ratingsRecords = ratings.map(line => line.split("::"))

// Check output with 
moviesRecords.take(3)
ratingsRecords.take(3)

// Return number of movies with at least one 10 rating
val ten_rating_movies = ratingsRecords
    .filter(record => record(2).toInt == 10) //selects the records with rating 10
    .map(record => record(1)) //maps records to movie id
    .distinct // to remove duplicates
    .count // count number of unique records that have 10 star rating

println(s"Number of movies with at least one 10 rating: $tenRatingMovies")

// Return earliest year with a movie
val movieYears = moviesRecords
  .map(record => record(1)) // Map to movie title
  .filter(title => title.matches(".*\\(\\d{4}\\)$")) // Filter titles that include a year
  .map(title => title.substring(title.length - 5, title.length - 1).toInt) // Map to year

val earliestYear = movieYears.min // Find the minimum year

// TASK 2
// Define an RDD usersWithNumOfRatings containing users (id) and their number of ratings
val usersWithNumOfRatings = ratingsRecords
    .map(record => (record(0), 1)) // Map to user id
    .reduceByKey(_ + _) // Reduce by key to get number of ratings per user

// sort the values to get the users with most ratings at the beginning
val usersWithNumOfRatingsSorted = usersWithNumOfRatings.sortBy(_._2, false)

// Return number of users with 20+ ratings
usersWithNumOfRatingsSorted
   .filter(_._2 >= 20)
   .count

// TASK 3
// Define an RDD containing (movie id, name)-pairs
val movie_id_name = moviesRecords
  .map(record => (record(0).toInt, record(1)))

// Define an RDD that cogroups movies with ratings over movie id

val movie_ratings = ratingsRecords
 .map(record => (record(1).toInt, record(2).toInt))

val joined = movie_id_name.cogroup(movie_ratings)

//Define an RDD that flattens out cogroup result to (movie name, rating)-pairs

val joinedFlattened = joined.flatMap{ case (_, (movieNames, ratings)) => movieNames.flatMap(movieName => ratings.map(rating => (movieName, rating)))}