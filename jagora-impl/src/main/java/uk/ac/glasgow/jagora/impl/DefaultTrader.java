package uk.ac.glasgow.jagora.impl;

import java.util.Set;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

/**
 * Implements a trader with behaviours for satisfying trades, but never speaks
 * on the exchange to place buy or sell orders.
 * 
 * @author tws
 *
 */
public class DefaultTrader implements Trader {
	
	private String name;
	private Double cash;
	private Stock stock;
	private Integer quantity;

	/**
	 * Constructs a new instance of default trader with the specified cash and a
	 * single stock of the specified quantity.
	 * 
	 * @param name
	 * @param cash
	 * @param stock
	 * @param quantity
	 */
	public DefaultTrader(String name, Double cash, Stock stock, Integer quantity) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getCash() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void speak(StockExchange stockExchange) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Stock> getTradingStocks() {
		// TODO Auto-generated method stub
		return null;
	}

}
