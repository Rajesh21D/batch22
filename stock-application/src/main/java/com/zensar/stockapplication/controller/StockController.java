package com.zensar.stockapplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.service.StockService;

@RestController
//@Controller
//@CrossOrigin("http://localhost:4200")
//@RequestMapping("/stocks")
@RequestMapping(value="/stocks",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	
	
	
/*	public StockController() {
		stocks.add(new Stock(1L, "RIL", "bse", 2610));
		stocks.add(new Stock(2L, "Zensar", "bse", 342));
		stocks.add(new Stock(3L, "Jio", "bse", 2210));
	} */
	
	/* @RequestMapping(value="/test",method=RequestMethod.GET)
	public void test() {
		System.out.println("I am inside test method");
	} */
	
	@GetMapping()
	//@ResponseBody
	//@RequestMapping(method=RequestMethod.GET)
	public List<Stock> getAllStocks() {
           return stockService.getAllStock();
}
	
/*	@GetMapping("/{stockId}")
	@RequestMapping(value="/{stockId}",method=RequestMethod.GET)
	public Stock getStock(@PathVariable("stockId") long id) {
		
		for(Stock stock:stocks) {
			if(stock.getStockId()== id) {
				return stock;
			}
		}
		return null;
	} */
	
	@GetMapping("/{stockId}")
	//@RequestMapping(value="/{stockId}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Stock getStock(@PathVariable("stockId") long id) {
		
	return stockService.getStock(id);
	} 
	
/*	@GetMapping("/stocks/stockId")
	public Stock getStockByRequestParam(@RequestParam(required=false,value="id",defaultValue="2") long id) {
		
		for(Stock stock:stocks) {
			if(stock.getStockId()== id) {
				return stock;
			}
		}
		return null;
	} */
	
	@PostMapping()
	//@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock, @RequestHeader("auth-token")String token) {
		Stock createStock =stockService.createStock(stock, token);
		
		return new  ResponseEntity<Stock>(createStock,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{stockId}")
	//@RequestMapping(value="/stocks",method=RequestMethod.DELETE)
	public String deleteStock(@PathVariable("stockId") long stockId) {
		return stockService.deleteStock(stockId);
	}
	
	@PutMapping("/{stockId}")
//	@RequestMapping(value="/stocks/{stockId}",method=RequestMethod.GET)
	public Stock updateStock(@PathVariable int stockId,@RequestBody Stock stock) {
		return stockService.updateStock(stockId, stock);
	} 
	
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks(){
	String returnResult=stockService.deleteAllStocks();

	return new ResponseEntity<String> (returnResult,HttpStatus.OK);

	//return stockService.deleteAllStocks()

	}
	
}