package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

public class LimitBuyOrder implements BuyOrder {
	
	private Trader trader;
	private Stock stock;
	private Integer quantity;
	private Double price;

	public LimitBuyOrder(Trader trader, Stock stock, Integer quantity, Double price) {
		//TODO
	}

	@Override
	public Double getPrice() {
		//TODO
		return null;
	}
	
	public String toString(){
		//TODO
		return null;
	}

	@Override
	public Trader getTrader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock getStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRemainingQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void satisfyTrade(TickEvent<Trade> tradeEvent) throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollBackTrade(TickEvent<Trade> tradeEvent)
			throws TradeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(BuyOrder order) {
		// TODO Auto-generated method stub
		return 0;
	}


}
