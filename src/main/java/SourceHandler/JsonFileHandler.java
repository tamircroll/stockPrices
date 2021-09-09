package SourceHandler;

import StocksHanlder.StocksPricesHandler;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileHandler extends StocksFileHandler
{
    JSONParser parser = new JSONParser();
    
    
    @Override
    void handle(String source, StocksPricesHandler stockPriceHandler)
    {
        try
        {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(source));
            
            for(Object json : jsonArray)
            {
                JSONObject stockJson = (JSONObject) json;
                String name = (String) stockJson.get("name");
                Double price = Double.valueOf((String) stockJson.get("price"));
                stockPriceHandler.addPrice(name, price);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to parse json");
        }
    }
    
    public boolean matches(String source)
    {
        return source.endsWith("json");
    }
}
