package uk.ac.glasgow.jagora.test.stub;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

public class StubStockExchange implements StockExchange {

	public final List<BuyOrder> buyOrders = new ArrayList<BuyOrder>();
	public final List<SellOrder> sellOrders = new ArrayList<SellOrder>();
	
	@Override
	public void doClearing() {
		// does nothing

	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		buyOrders.add(buyOrder);

	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		sellOrders.add(sellOrder);
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		// does nothing

	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		// does nothing

	}

	@Override
	public List<TickEvent<Trade>> getTradeHistory(Stock stock) {
		// does nothing - no trades executed.
		return null;
	}

	@Override
	public Double getBestOffer(Stock stock) {
		return 5.0;
	}

	@Override
	public Double getBestBid(Stock stock) {
		return 5.0;
	}

}
