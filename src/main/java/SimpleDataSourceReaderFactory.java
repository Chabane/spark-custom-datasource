import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.sources.v2.reader.DataReader;
import org.apache.spark.sql.sources.v2.reader.DataReaderFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SimpleDataSourceReaderFactory implements DataReaderFactory<Row>, DataReader<Row> {

        @Setter
        @Getter
        @ToString
        private class Airport {
                private String ident; 
                private String type; 
                private String name; 
                private String coordinates; 
                private String elevationFt; 
                private String continent; 
                private String isoCountry; 
                private String isoRegion; 
                private String municipality; 
                private String gpsCode; 
                private String iataCode; 
                private String localCode;
        }
        
        List<Airport> airports;
        
        public SimpleDataSourceReaderFactory() {
                 try {
            Reader reader = Files.newBufferedReader(Paths.get("./csv/airport-codes.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
                    
                    airports = new ArrayList<>();
                    
                    for (CSVRecord csvRecord : csvParser) {
                        Airport airport = new Airport();
                        airport.setIdent(csvRecord.get("ident")); 
                        airport.setType(csvRecord.get("type")); 
                        airport.setName(csvRecord.get("name")); 
                        airport.setCoordinates(csvRecord.get("coordinates")); 
                        airport.setElevationFt(csvRecord.get("elevation_ft")); 
                        airport.setContinent(csvRecord.get("continent")); 
                        airport.setIsoCountry(csvRecord.get("iso_country")); 
                        airport.setIsoRegion(csvRecord.get("iso_region")); 
                        airport.setMunicipality(csvRecord.get("municipality")); 
                        airport.setGpsCode(csvRecord.get("gps_code")); 
                        airport.setIataCode(csvRecord.get("iata_code")); 
                        airport.setLocalCode(csvRecord.get("local_code"));
                        airports.add(airport);
                    }
                } catch(Exception e) {
                   System.out.println("Error occured"+e);
                }
        }
        
        public DataReader createDataReader() {
            return new SimpleDataSourceReaderFactory();
        }

        Integer index = 0;

        public boolean next() {
            return index < airports.size();
        }

        public Row get() {
            Airport airport = airports.get(index);
            Row row = RowFactory.create(airport.getIdent());
            index = index + 1;
            return row;
        }

        public void close() {}
}
