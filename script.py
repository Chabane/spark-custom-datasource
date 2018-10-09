from pyspark.sql import SparkSession

if __name__ == "__main__":

    spark = SparkSession \
    .builder \
    .config("spark.python.profile", "true") \
    .config("spark.sql.execution.arrow.enabled", "true") \
    .getOrCreate()

    df = spark.read.format("CustomDataSource").load()
    df.printSchema()

    pdf = df.toPandas()
  
    spark.sparkContext.show_profiles()
