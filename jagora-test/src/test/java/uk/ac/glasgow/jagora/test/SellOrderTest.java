package uk.ac.glasgow.jagora.test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.tradeEvent;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.Trader;

/**
 * Tests the abstract behaviour of any concrete sell order.
 * @author tws
 */
@Ignore
public abstract class SellOrderTest extends OrderTest  {

	protected SellOrder sellOrder0;
	protected SellOrder sellOrder1;
		
	@Test
	public void testTraderDeducted() throws Exception {		
		Trade trade = tradeEvent.getEvent();
		Trader trader = sellOrder0.getTrader();
		Stock stock = sellOrder0.getStock();
		
		Integer initialTraderQuantity = trader.getInventoryHolding(stock);
		sellOrder0.satisfyTrade(tradeEvent);
		Integer finalTraderQuantity = trader.getInventoryHolding(stock);
		assertEquals("", initialTraderQuantity - trade.getQuantity(), finalTraderQuantity.intValue());
	}

	@Test
	public void testTraderRolledBack() throws Exception {		
		Trader trader = sellOrder0.getTrader();
		Stock stock = sellOrder0.getStock();
		
		Integer initialTraderQuantity = trader.getInventoryHolding(stock);
		sellOrder0.satisfyTrade(tradeEvent);
		sellOrder0.rollBackTrade(tradeEvent);
		Integer finalTraderQuantity = trader.getInventoryHolding(stock);
		
		assertEquals("", initialTraderQuantity.intValue(), finalTraderQuantity.intValue());
	}

	@Test
	public void testCompareTo() {
		assertThat(0, greaterThan(sellOrder0.compareTo(sellOrder1)));
	}

}
