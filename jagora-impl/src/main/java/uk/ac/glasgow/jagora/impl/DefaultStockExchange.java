package uk.ac.glasgow.jagora.impl;

import java.util.List;
import java.util.Map;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;

public class DefaultStockExchange implements StockExchange {

	private Map<Stock,Market> markets;
	private World world;
	private List<TickEvent<Trade>> tradeHistory;
	
	public DefaultStockExchange(World world){
	}
	
	@Override
	public void doClearing() {
		//TODO
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
	public Double getBestOffer(Stock stock) {
		//TODO
		return null;
	}

	@Override
	public Double getBestBid(Stock stock) {
		//TODO
		return null;
	}
	
	@Override
	public List<TickEvent<Trade>> getTradeHistory(Stock stock) {
		//TODO
		return null;
	}

}
