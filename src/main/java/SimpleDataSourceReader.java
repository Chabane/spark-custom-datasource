import org.apache.spark.sql.Row;
import org.apache.spark.sql.sources.v2.reader.DataReaderFactory;
import org.apache.spark.sql.sources.v2.reader.DataSourceReader;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleDataSourceReader implements DataSourceReader {

    public StructType readSchema() {
        StructField[] structFields = new StructField[]{
                new StructField("value", DataTypes.StringType, true, Metadata.empty())
        };

        StructType structType = new StructType(structFields);
        return structType;
    }

    public List<DataReaderFactory<Row>> createDataReaderFactories() {
        List<DataReaderFactory<Row>> factoryList = new ArrayList<>();
        factoryList.add(new SimpleDataSourceReaderFactory());
        return factoryList;
    }
}