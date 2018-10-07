import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.sources.v2.reader.DataReader;
import org.apache.spark.sql.sources.v2.reader.DataReaderFactory;

public class SimpleDataSourceReaderFactory implements DataReaderFactory<Row>, DataReader<Row> {

        public DataReader createDataReader() {
            return new SimpleDataSourceReaderFactory();
        }

        String[] values = {"1", "2", "3", "4", "5"};

        Integer index = 0;

        public boolean next() {
            return index < values.length;
        }

        public Row get() {
            Row row = RowFactory.create(values[index]);
            index = index + 1;
            return row;
        }

        public void close() {}
}
