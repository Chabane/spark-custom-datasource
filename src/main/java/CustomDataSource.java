import org.apache.spark.sql.sources.v2.DataSourceOptions;
import org.apache.spark.sql.sources.v2.DataSourceV2;
import org.apache.spark.sql.sources.v2.ReadSupport;

import org.apache.spark.sql.sources.v2.reader.DataSourceReader;

public class CustomDataSource implements DataSourceV2, ReadSupport {

    @Override
    public DataSourceReader createReader(DataSourceOptions var1) {
        return new SimpleDataSourceReader();
    }
}