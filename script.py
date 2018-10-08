from pyspark.sql import SparkSession

if __name__ == "__main__":

    spark = SparkSession \
    .builder \
    .appName("spark-custom-datasource") \
    .master("local[2]") \
    .config("spark.python.profile", "true") \
    .getOrCreate()
    
    df = spark.read.format("com.evid.CustomDataSource").load()
    df.show()
  
    spark.sparkContext.show_profiles()
