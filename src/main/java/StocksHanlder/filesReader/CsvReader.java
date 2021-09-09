package StocksHanlder.filesReader;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader
{
//    Map<String, Integer> columnNameToIndex = new HashMap<>(); Todo: add mapping from column name to Index

    private List<String[]> r;
    
    
//    public CsvReader(String source)
//    {
//        parse(source);
//    }
    
    public List<String[]> parse(String source)
    {
        try (CSVReader reader = new CSVReader(new FileReader(source)))
        {
            return reader.readAll();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: failed to parse .csv file");
            return new ArrayList<>();
        }
    }
}