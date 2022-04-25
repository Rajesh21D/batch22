package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.entity.Stock;

public interface StockService {
List<Stock> getAllStock();
	
	Stock getStock(long id);
	
	Stock createStock( Stock stock,String token);
	
	String deleteStock( long stockId);
	
	Stock updateStock( int stockId, Stock stock);
	
	String deleteAllStocks();
	

}
