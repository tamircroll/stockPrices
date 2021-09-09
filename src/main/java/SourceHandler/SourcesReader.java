package SourceHandler;

import StocksHanlder.StocksPricesHandler;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SourcesReader
{
    private final StocksPricesHandler stocksFileHandler;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final List<StocksFileHandler> stocksFileHandlers;
    ExecutorService fileHandlerExecutor = Executors.newFixedThreadPool(4);
    ExecutorService webFilesExecutor = Executors.newFixedThreadPool(100);
    
    public SourcesReader(TimeUnit readSourceTimeUnit, int readSourceDelay, StocksPricesHandler stocksFileHandler, List<StocksFileHandler> stocksFileHandlers)
    {
        this.stocksFileHandler = stocksFileHandler;
        this.stocksFileHandlers = stocksFileHandlers;
        scheduler.scheduleAtFixedRate(this::read, 0, readSourceDelay, readSourceTimeUnit);
    }
    private void read()
    {
        List<String> sources = getSources();
        List<String> webSources = sources.stream().filter(source -> source.startsWith("http")).toList();
        List<String> localSources = sources.stream().filter(source -> source.startsWith("http")).toList();
        
        for(String source : webSources)
        {
            webFilesExecutor.execute(() -> handleWebFile(source));
        }
        for(String source : localSources)
        {
            fileHandlerExecutor.execute(() -> handleLocalFile(source));
        }
    }
    
    private void handleWebFile(String source)
    {
        String fileName = Paths.get(source).getFileName().toString();
        String fullPath = MessageFormat.format("c:\\tempFiles\\{0}", fileName);
        
        try
        {
            InputStream in = new URL(source).openStream();
            Files.copy(in, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e)
        {
            System.out.println("Failed to download file");
        }
        fileHandlerExecutor.execute(() -> handleLocalFile(fullPath));
    }
    
    
    private void handleLocalFile(String source)
    {
        for(StocksFileHandler stocksFileHandlers : stocksFileHandlers)
        {
            if(stocksFileHandlers.matches(source))
            {
                stocksFileHandlers.handle(source, stocksFileHandler);
                break;
            }
        }
    }
    
    // Handle some kind of source
    private ArrayList getSources()
    {
        return new ArrayList();
    }
}
