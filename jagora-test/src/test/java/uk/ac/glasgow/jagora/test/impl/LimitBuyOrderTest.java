package uk.ac.glasgow.jagora.test.impl;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.impl.DefaultTrader;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.test.BuyOrderTest;

public class LimitBuyOrderTest extends BuyOrderTest {

	@Before
	public void setUp () throws Exception {
		Trader buyer = new DefaultTrader("default", 500.0, lemons, 0);
		buyOrder0 = new LimitBuyOrder (buyer, lemons, 1, 0.1);
		buyOrder1 =	new LimitBuyOrder (buyer, lemons, 1, 0.05);
		order0 = buyOrder0;
		order1 = buyOrder1;
		sameAsOrder1 = new LimitBuyOrder (buyer, lemons, 1, 0.05);
		
		nullPriceOrder1 = new LimitBuyOrder (buyer, lemons, 1, null);
		nullPriceOrder2 = new LimitBuyOrder (buyer, lemons, 1, null);

	}
	
	@Test
	public void testGetPrice() {
		assertEquals("", 0.1, buyOrder0.getPrice(), 0.0);
	}
}
