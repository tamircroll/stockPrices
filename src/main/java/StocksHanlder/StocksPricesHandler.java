package StocksHanlder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StocksPricesHandler extends IStocksPricesHandler {
    private ConcurrentHashMap<String, StockData> stocks = new ConcurrentHashMap();
    
    @Override
    public void addPrice(String stockName, double stockPrice) {
        stocks.putIfAbsent(stockName, new StockData(stockName));
        StockData stock = stocks.get(stockName);
        stock.addPrice(stockPrice);
    }
    
    @Override
    public double getLowestPrice(String stockName) {
        StockData stock = stocks.get(stockName);
        if (stock != null) {
            return stock.getLowestPrice();
        }
        else {
            return -1;
        }
    }
    
    public List<Double> getAllLowestPrices() {
        List<Double> res = new ArrayList();
        Iterator<StockData> iterator = stocks.values().iterator();
        
        while (iterator.hasNext()) {
            Double price = iterator.next().getLowestPrice();
            res.add(price);
        }
        return res;
    }
}
