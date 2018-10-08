
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.sources.v2.reader.DataReader;
import org.apache.spark.sql.sources.v2.reader.DataReaderFactory;

public class SimpleDataSourceReaderFactory implements DataReaderFactory<Row>, DataReader<Row> {

        String[][] airports = {{"00A","heliport","Total Rf Heliport","-74.93360137939453, 40.07080078125","11","NA","US","US-PA","Bensalem","00A","","00A"
}, {"00AA","small_airport","Aero B Ranch Airport","-101.473911, 38.704022","3435","NA","US","US-KS","Leoti","00AA","","00AA"}};
        
        public DataReader createDataReader() {
            return new SimpleDataSourceReaderFactory();
        }

        Integer index = 0;

        public boolean next() {
            return index < airports.length;
        }

        public Row get() {
            Row row = RowFactory.create(airports[index]);
            index = index + 1;
            return row;
        }

        public void close() {}
}
