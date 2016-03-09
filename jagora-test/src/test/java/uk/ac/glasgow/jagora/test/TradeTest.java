package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

@Ignore
public abstract class TradeTest {

	protected Integer quantity = 1;
	protected Double price = 1.0;
	
	protected SellOrder sellOrder;
	protected BuyOrder buyOrder;
	
	protected Trade trade;
		
	@Test
	public void testGetStock() {
		assertEquals(lemons, trade.getStock());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(quantity.intValue(), trade.getQuantity().intValue());
	}

	@Test
	public void testGetPrice() {
		assertEquals(price, trade.getPrice(), 0.0);
	}

	@Test
	public void testExecuteCreatesTick() throws Exception {
		TickEvent<Trade> tradeTick = trade.execute();
		assertEquals(trade, tradeTick.getEvent());
	}
	
	@Test
	public void testExecuteFillsSellOrder () throws Exception{
		Integer initialQuantity = sellOrder.getRemainingQuantity();
		trade.execute();
		assertEquals(initialQuantity - quantity, sellOrder.getRemainingQuantity().intValue());
	}

	@Test
	public void testExecuteFillsBuyOrder () throws Exception{
		Integer initialQuantity = buyOrder.getRemainingQuantity();
		trade.execute();
		assertEquals(initialQuantity - quantity, buyOrder.getRemainingQuantity().intValue());
	}

}
