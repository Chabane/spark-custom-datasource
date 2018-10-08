import org.apache.spark.sql.Row;
import org.apache.spark.sql.sources.v2.reader.DataReaderFactory;
import org.apache.spark.sql.sources.v2.reader.DataSourceReader;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleDataSourceReader implements DataSourceReader {

    public StructType readSchema() {
         StructField[] structFields = new StructField[]{
                new StructField("ident", DataTypes.StringType, true, Metadata.empty()),
                new StructField("type", DataTypes.StringType, true, Metadata.empty()),
                new StructField("name", DataTypes.StringType, true, Metadata.empty()),
                new StructField("coordinates", DataTypes.StringType, true, Metadata.empty()),
                new StructField("elevation_ft", DataTypes.StringType, true, Metadata.empty()),
                new StructField("continent", DataTypes.StringType, true, Metadata.empty()),
                new StructField("iso_country", DataTypes.StringType, true, Metadata.empty()),
                new StructField("iso_region", DataTypes.StringType, true, Metadata.empty()),
                new StructField("municipality", DataTypes.StringType, true, Metadata.empty()),
                new StructField("gps_code", DataTypes.StringType, true, Metadata.empty()),
                new StructField("iata_code", DataTypes.StringType, true, Metadata.empty()),
                new StructField("local_code", DataTypes.StringType, true, Metadata.empty())
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
