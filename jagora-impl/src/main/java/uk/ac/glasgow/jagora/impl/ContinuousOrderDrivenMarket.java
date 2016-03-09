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
		this.stock = stock;
		this.world = world;
		sellBook = new DefaultOrderBook<>(world);
		buyBook = new DefaultOrderBook<>(world);
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	@Override
	public List<TickEvent<Trade>> doClearing() {
		//TODO
		return null;		
	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		buyBook.recordOrder(buyOrder);
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		sellBook.recordOrder(sellOrder);
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		buyBook.cancelOrder(buyOrder);
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		sellBook.cancelOrder(sellOrder);
	}

	@Override
	public Double getBestBid() {
		return buyBook.getBestOrder().getPrice();
	}

	@Override
	public Double getBestOffer() {
		return sellBook.getBestOrder().getPrice();
	}
	
	@Override
	public String toString(){
		//TODO
		return null;
	}
}
