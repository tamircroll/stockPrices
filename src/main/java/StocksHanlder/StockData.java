package StocksHanlder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockData
{
    private double lowestPrice = Double.MAX_VALUE;
    private final String m_stockName;
    List<Double> pricesQueue = Collections.synchronizedList(new ArrayList<Double>());
    
    public StockData(String StockName)
    {
        m_stockName = StockName;
    }
    
    public void addPrice(double price)
    {
        pricesQueue.add(price);
        if(price < lowestPrice)
        {
            synchronized (this)
            {
                if(price < lowestPrice)
                {
                    lowestPrice = price;
                }
            }
        }
    }
    
    public double getLowestPrice()
    {
        return lowestPrice;
    }
}
