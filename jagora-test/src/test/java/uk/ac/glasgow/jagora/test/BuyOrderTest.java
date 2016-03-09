package uk.ac.glasgow.jagora.test;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.tradeEvent;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.Trader;

/**
 * Tests the abstract behaviour of any concrete buy order.
 * @author tws
 */
@Ignore
public abstract class BuyOrderTest extends OrderTest {

	protected BuyOrder buyOrder0;
	protected BuyOrder buyOrder1;

	@Test
	public void testTraderCredited() throws Exception {		
		Trade trade = tradeEvent.getEvent();
		Trader trader = buyOrder0.getTrader();
		Stock stock = buyOrder0.getStock();
		
		Integer initialTraderQuantity = trader.getInventoryHolding(stock);
		buyOrder0.satisfyTrade(tradeEvent);
		Integer finalTraderQuantity = trader.getInventoryHolding(stock);
		assertEquals("", initialTraderQuantity + trade.getQuantity(), finalTraderQuantity.intValue());
	}

	@Test
	public void testTraderRolledBack() throws Exception {		
		Trader trader = buyOrder0.getTrader();
		Stock stock = buyOrder0.getStock();
		
		Integer initialTraderQuantity = trader.getInventoryHolding(stock);
		buyOrder0.satisfyTrade(tradeEvent);
		buyOrder0.rollBackTrade(tradeEvent);
		Integer finalTraderQuantity = trader.getInventoryHolding(stock);
		
		assertEquals("", initialTraderQuantity.intValue(), finalTraderQuantity.intValue());
	}

	@Test
	public void testCompareTo() {
		assertThat(buyOrder0, lessThan(buyOrder1));
	}
}
