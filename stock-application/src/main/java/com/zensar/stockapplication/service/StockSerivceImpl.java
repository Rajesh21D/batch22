package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.entity.Stock;
@Service
public class StockSerivceImpl implements StockService {
static List<Stock> stocks= new ArrayList<Stock>();
	
	static {
		stocks.add(new Stock(1L, "RIL", "bse", 2610));
		stocks.add(new Stock(2L, "Zensar", "bse", 342));
		stocks.add(new Stock(3L, "Jio", "bse", 2210));
	}
	@Override
	public List<Stock> getAllStock() {
		
		return stocks;
	}

	@Override
	public Stock getStock(long id) {
		Optional<Stock> stock1=	stocks.stream().filter(stock -> stock.getStockId()==id).findAny();
		
		if(stock1.isPresent()){
			return stock1.get();
		}else {
			return stock1.orElseGet(()->{return new Stock();});
		}
	}

	@Override
	public Stock createStock(Stock stock, String token) {
		if(token.equals("DR66458")) {
			stocks.add(stock);
			return stock;
			}else {
				return null ;
			}
			
	}

	@Override
	public String deleteStock(long stockId) {
		for(Stock stock:stocks) {
			if(stock.getStockId()== stockId) {
				stocks.remove(stock);
				return " Stock deleted with stock id "+stockId;
			}
		}
		return "Sorry,stock id is not available";
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {
		Stock availableStock= getStock(stockId);
		availableStock.setMarketName(stock.getMarketName());
		availableStock.setName(stock.getName());
		availableStock.setPrice(stock.getPrice());
		
		return availableStock;
	}

	@Override
	public String deleteAllStocks() {
		stocks.removeAll(stocks);
		return ("All stocks deleted successfullyy");
	}

	

}
