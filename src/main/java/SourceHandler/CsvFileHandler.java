package SourceHandler;

import StocksHanlder.StocksPricesHandler;
import StocksHanlder.filesReader.CsvReader;
import java.util.List;

public class CsvFileHandler extends StocksFileHandler {
    private static final int PRICE_INDEX = 2;
    private static final int NAME_INDEX = 0;
    
    
    @Override
    public void handle(String source, StocksPricesHandler stockPriceHandler) {
        CsvReader reader = new CsvReader();
        List<String[]> stocks = reader.parse(source);
        for(String[] stock : stocks) {
            double price = Double.parseDouble(stock[PRICE_INDEX]);
            stockPriceHandler.addPrice(stock[NAME_INDEX], price);
        }
    }
    
    @Override
    public boolean matches(String source) {
        return source.endsWith("csv");
    }
}
