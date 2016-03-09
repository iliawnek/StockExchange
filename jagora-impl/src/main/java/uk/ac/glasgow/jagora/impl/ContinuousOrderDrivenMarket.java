package uk.ac.glasgow.jagora.impl;


import java.util.List;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.OrderBook;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;

/**
 * Provides the behaviour of a continuous order driven market.
 * @author tws
 *
 */
public class ContinuousOrderDrivenMarket implements Market {
	
	private OrderBook<SellOrder> sellBook;
	private OrderBook<BuyOrder> buyBook;
	private Stock stock;
	private World world;
	
	/**
	 * Constructs a new continuous order driven market for the specified stock,
	 * synchronised to the tick events of the specified world.
	 * 
	 * @param stock
	 * @param world
	 */
	public ContinuousOrderDrivenMarket(Stock stock, World world) {
		//TODO
	}

	@Override
	public Stock getStock() {
		//TODO
		return null;
	}

	@Override
	public List<TickEvent<Trade>> doClearing() {
		//TODO
		return null;		
	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		//TODO
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		//TODO
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		//TODO
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		//TODO
	}

	@Override
	public Double getBestBid() {
		//TODO
		return null;
	}

	@Override
	public Double getBestOffer() {
		//TODO
		return null;
	}
	
	@Override
	public String toString(){
		//TODO
		return null;
	}
}
