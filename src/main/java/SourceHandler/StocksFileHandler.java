package SourceHandler;

import StocksHanlder.StocksPricesHandler;

public abstract class StocksFileHandler
{
    abstract void handle(String source, StocksPricesHandler stockPriceHandler);
    
    abstract boolean matches(String source);
}
