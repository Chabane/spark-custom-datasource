
from pyspark.sql import SparkSession

if __name__ == "__main__":

    spark = SparkSession \
    .builder \
    .appName("spark-custom-datasource") \
    .getOrCreate()
    df = spark.read.format("CustomDataSource").load()
    df.show()
   
    spark.sparkContext.show_profiles()
