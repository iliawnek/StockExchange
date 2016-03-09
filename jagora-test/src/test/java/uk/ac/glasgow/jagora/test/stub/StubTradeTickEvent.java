package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

public class StubTradeTickEvent implements TickEvent<Trade> {
	
	private Trade event;
	private Long tick;

	public StubTradeTickEvent(Long tick, Stock stock, Integer quantity, Double price) {
		event = new StubTrade(stock, quantity, price);
	}

	@Override
	public int compareTo(TickEvent<Trade> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Trade getEvent() {
		return event;
	}

	@Override
	public Long getTick() {
		return tick;
	}

}
