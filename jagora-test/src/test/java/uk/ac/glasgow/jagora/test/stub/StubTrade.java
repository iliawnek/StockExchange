package uk.ac.glasgow.jagora.test.stub;

import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.TradeException;

public class StubTrade implements Trade {
	
	public final static Trade trade = new StubTrade (lemons, 1, 0.1);
	public final static TickEvent<Trade> tradeEvent = new StubTradeTickEvent(1l, lemons, 1, 0.1);
	public final static Double[] expectedPrices = new Double[]{0.2, 0.2, 0.5, 0.5};
	
	private Stock stock;
	private Integer quantity;
	private Double price;

	protected StubTrade(Stock stock, Integer quantity, Double price) {
		this.quantity = quantity;
		this.price = price;
		this.stock = stock;
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public TickEvent<Trade> execute() throws TradeException {
		throw new UnsupportedOperationException("just a stub.");
	}

}
