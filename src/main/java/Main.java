import SourceHandler.*;
import StocksHanlder.StocksPricesHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {
        StocksPricesHandler stockPriceHandler = new StocksPricesHandler();
        
        CsvFileHandler csv = new CsvFileHandler();
        JsonFileHandler json = new JsonFileHandler();
    
        List<StocksFileHandler> stocksFileHandlers = createHandlersList(csv, json);
        SourcesReader sourcesReader = new SourcesReader(TimeUnit.SECONDS, 20, stockPriceHandler, stocksFileHandlers);
    
        List<Double> allLowestPrices = stockPriceHandler.getAllLowestPrices();
        double appl = stockPriceHandler.getLowestPrice("APPL");
    
        System.out.println("Main thread done");
    }
    
    private static List<StocksFileHandler> createHandlersList(CsvFileHandler csv, JsonFileHandler json)
    {
        List<StocksFileHandler> stocksFileHandlers = new ArrayList();
        stocksFileHandlers.add(csv);
        stocksFileHandlers.add(json);
        return stocksFileHandlers;
    }
}
