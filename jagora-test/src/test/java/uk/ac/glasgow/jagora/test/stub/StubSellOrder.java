package uk.ac.glasgow.jagora.test.stub;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.seller;

import java.util.List;

import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

public class StubSellOrder implements SellOrder {

	public static final List<SellOrder> stubSellOrders = 
		unmodifiableList(asList(new SellOrder[]{
			new StubSellOrder(seller, lemons, 2, 0.2),
			new StubSellOrder(seller, lemons, 1, 0.5),
			new StubSellOrder(seller, lemons, 1, 0.5),
			new StubSellOrder(seller, lemons, 1, 2.5)}));
	
	private final Trader trader;
	private final Double price;
	private final Stock stock;
	private final Integer quantity;
	
	private StubSellOrder(Trader trader, Stock stock, Integer quantity, Double price) {
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
	public int compareTo(SellOrder order) {
		return stubSellOrders.indexOf(this) - stubSellOrders.indexOf(order);

	}

}
