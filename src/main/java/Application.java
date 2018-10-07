import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Application {

	public static void main(String[] args) {

		SparkSession sparkSession = SparkSession
				.builder()
				.master("local[*]")
				.appName("spark-custom-datasource")
				.getOrCreate();

		Dataset<Row> simpleDf = sparkSession.read()
				.format("CustomDataSource").load();

		simpleDf.show();
		System.out.println("number of partitions in simple source is "+simpleDf.rdd().getNumPartitions());
	}
}