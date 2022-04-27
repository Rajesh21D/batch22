package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.entity.StockRequest;
import com.zensar.stockapplication.entity.StockResponse;
import com.zensar.stockapplication.repository.StockRepository;
@Service
public class StockSerivceImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;
	

	@Override
	public List<StockResponse> getAllStock(int pageNumber,int pageSize) {
		
	Page<Stock> pageStocks= stockRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by("marketName").descending()));	
		List<Stock> content = pageStocks.getContent();
		List<StockResponse> stockResponses=new ArrayList<>();
		for(Stock stock : content) {
			StockResponse mapToResponse = mapToResponse(stock);
			stockResponses.add(mapToResponse);
		}
		
		return stockResponses;
	}

	@Override
	public StockResponse getStock(long id) {
		
		Stock stock= stockRepository.findById(id).get();
		StockResponse stockResponse=new StockResponse();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());
		stockResponse.setPrice(stock.getPrice());
		
		return stockResponse;
	/* Optional<Stock> optStock=	stockRepository.findById(stockId);
		
	if(optStock.isPresent()) {
		return optStock.get();
	}else {
		return optStock.orElseGet(()->{return new Stock();}); 
	} */
		
	/*	Optional<Stock> stock1=	stocks.stream().filter(stock -> stock.getStockId()==id).findAny();
		
		if(stock1.isPresent()){
			return stock1.get();
		}else {
			return stock1.orElseGet(()->{return new Stock();});
		} */
	}

	@Override
	public StockResponse createStock(StockRequest stock, String token) {
	//	return stockRepository.save(stock);
		Stock newStock=mapToStock(stock);
		if(token.equals("DR66458")) {
			Stock save= stockRepository.save(newStock);
			return mapToResponse(save);
			}else {
				return null ;
			} 
			
	}

	@Override
	public String deleteStock(long stockId) {
		
		stockRepository.deleteById(stockId);
		return " Stock deleted with stock id "+stockId;
	/*	for(Stock stock:stocks) {
			if(stock.getStockId()== stockId) {
				stocks.remove(stock);
				return " Stock deleted with stock id "+stockId;
			}
		}
		return "Sorry,stock id is not available"; */
	}

	@Override
	public StockResponse updateStock(int stockId, StockRequest stock) {
		
		StockResponse stockResponse =getStock(stockId);
		Stock stock2=mapToStock(stockResponse);
		if(stock2!=null) {
			stock2.setPrice(stock.getPrice());
			stock2.setMarketName(stock.getMarketName());
			stock2.setName(stock.getName());
			stock2.setStockId(stockId);
		Stock stock3=	stockRepository.save(stock2);
	return	mapToResponse(stock3);
		}
		
		return null;
	}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		//stocks.removeAll(stocks);
		return ("All stocks deleted successfullyy");
	}

	private Stock mapToStock(StockRequest stockRequest) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockRequest.getMarketName());
		newStock.setName(stockRequest.getName());
		newStock.setPrice(stockRequest.getPrice());
		return newStock;
	}
	private Stock mapToStock(StockResponse stockResponse) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockResponse.getMarketName());
		newStock.setName(stockResponse.getName());
		newStock.setPrice(stockResponse.getPrice());
		return newStock;
	}
	private StockResponse mapToResponse(Stock stock) {
		
		StockResponse stockResponse=new StockResponse();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setPrice(stock.getPrice());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());
		
		return stockResponse;
	}

}
