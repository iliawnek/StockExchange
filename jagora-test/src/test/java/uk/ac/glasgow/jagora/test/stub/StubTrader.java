package uk.ac.glasgow.jagora.test.stub;

import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

public class StubTrader implements Trader {
	
	private Double cash;
	private String name;
	private Map<Stock,Integer> inventory;
	
	public static final Trader seller = new 
		StubTrader("seller", 0.0,createSellerInventory());
		
	public static final Trader buyer = new
		StubTrader("buyer", 10.0, new HashMap<Stock,Integer>());


	public StubTrader(String name, Double cash, Map<Stock, Integer> inventory) {
		this.name = name;
		this.cash = cash;
		this.inventory = inventory;
	}

	@Override
	public void speak(StockExchange stockExchange) {
		// Does nothing.
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double getCash() {
		return cash;
	}

	@Override
	public void sellStock(Stock stock, Integer quantity, Double price)
			throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyStock(Stock stock, Integer quantity, Double price)
			throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getInventoryHolding(Stock stock) {
		return inventory.get(lemons);
	}

	@Override
	public Set<Stock> getTradingStocks() {
		return Collections.singleton(lemons);
	}
	
	private static Map<Stock, Integer> createSellerInventory() {
		Map<Stock,Integer> inventory = new HashMap<Stock,Integer>();
		inventory.put(lemons, 10);
		return inventory;
	}

}
