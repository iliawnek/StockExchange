package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.World;

public class DefaultTrade implements Trade {

	private World world;
	private BuyOrder buyOrder;
	private SellOrder sellOrder;
	private Integer quantity;
	private Stock stock;
	private Double price;

	public DefaultTrade(World world, BuyOrder buyOrder, SellOrder sellOffer, Stock stock, Integer quantity, Double price) {
		//TODO
	}

	@Override
	public Stock getStock() {
		//TODO
		return null;
	}

	@Override
	public Integer getQuantity() {
		//TODO
		return null;
	}

	@Override
	public Double getPrice() {
		//TODO
		return null;
	}

	@Override
	public TickEvent<Trade> execute() throws TradeException {		
		//TODO
		return null;
	}
	
	public String toString(){
		//TODO
		return null;
	}

}
