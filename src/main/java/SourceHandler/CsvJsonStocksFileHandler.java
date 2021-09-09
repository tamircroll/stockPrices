//package SourceHandler;
//
//public class CsvJsonStocksFileHandler extends StocksFileHandler
//{
//    private CsvFileHandler csvHandler;
//    private JsonFileHandler jsonHandler;
//
//    public CsvJsonStocksFileHandler(CsvFileHandler csvHandler, JsonFileHandler jsonHandler)
//    {
//        this.csvHandler = csvHandler;
//        this.jsonHandler = jsonHandler;
//    }
//
//    @Override
//    void handle(String source)
//    {
//        if(source.endsWith("csv"))
//        {
//            csvHandler.handle(source);
//        }
//        else if(source.endsWith("json"))
//        {
//            jsonHandler.handle(source);
//        }
//        else
//        {
//            System.out.println("file not supported");
//        }
//    }
//}
