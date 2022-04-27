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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.entity.StockRequest;
import com.zensar.stockapplication.entity.StockResponse;
import com.zensar.stockapplication.service.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController
//@Controller
//@CrossOrigin("http://localhost:4200")
//@RequestMapping("/stocks")
@RequestMapping(value="/stocks")
//@RequestMapping(produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//@Api(value= "This is stock controller")
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
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	//@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value= "Getting All Stock Info")
	//@ApiIgnore
	public List<StockResponse> getAllStocks(@RequestParam(value="pageNumber", defaultValue="0", required=false) int pageNumber,@RequestParam(value="pageSize", defaultValue="5", required=false) int pageSize) {
		System.out.println(pageSize);
           return stockService.getAllStock( pageNumber,pageSize);
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
	
	@GetMapping(value="/{stockId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	//@RequestMapping(value="/{stockId}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Getting stock based on stock id")
	@ApiResponse( message = "Got the stock of given stock id",code = 200)
	public StockResponse getStock(@ApiParam("stock id has to be greater than 1") @PathVariable("stockId") long id) {
		
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
	
	@PostMapping(produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	//@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<StockResponse> createStock(@RequestBody StockRequest stock, @RequestHeader("auth-token")String token) {
		StockResponse createStock =stockService.createStock(stock, token);
		
		return new  ResponseEntity<StockResponse>(createStock,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{stockId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	//@RequestMapping(value="/stocks",method=RequestMethod.DELETE)
	public String deleteStock(@PathVariable("stockId") long stockId) {
		return stockService.deleteStock(stockId);
	}
	
	@PutMapping(value="/{stockId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//	@RequestMapping(value="/stocks/{stockId}",method=RequestMethod.GET)
	public StockResponse updateStock(@PathVariable int stockId,@RequestBody StockRequest stock) {
		return stockService.updateStock(stockId, stock);
	} 
	
	@DeleteMapping(produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteAllStocks(){
	String returnResult=stockService.deleteAllStocks();

	return new ResponseEntity<String> (returnResult,HttpStatus.OK);

	//return stockService.deleteAllStocks()

	}
	
}