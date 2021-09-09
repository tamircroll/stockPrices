package StocksHanlder;

import java.util.List;

public abstract class IStocksPricesHandler
{
    abstract void addPrice(String stockName, double stockPrice);
    abstract double getLowestPrice(String stockName);
    abstract public List<Double> getAllLowestPrices();
    
}
