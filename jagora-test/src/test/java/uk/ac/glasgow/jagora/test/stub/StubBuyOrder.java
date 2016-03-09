package uk.ac.glasgow.jagora.test.stub;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.buyer;

import java.util.List;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

public class StubBuyOrder implements BuyOrder {
	
	public static final List<BuyOrder> stubBuyOrders = 
		unmodifiableList(asList(new BuyOrder[]{
			new StubBuyOrder(buyer, lemons, 1, 2.5),
			new StubBuyOrder(buyer, lemons, 1, 1.0),
			new StubBuyOrder(buyer, lemons, 1, 0.5),
			new StubBuyOrder(buyer, lemons, 1, 0.5)}));
	
	private final Double price;
	private final Trader trader;
	private final Stock stock;
	private final Integer quantity;

	protected StubBuyOrder(Trader trader, Stock stock, Integer quantity, Double price) {
		this.trader = trader;
		this.stock = stock;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public Trader getTrader() {
		return trader;
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	@Override
	public Integer getRemainingQuantity() {
		return quantity;
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
		return stubBuyOrders.indexOf(this) - stubBuyOrders.indexOf(order);
	}
}
