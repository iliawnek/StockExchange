package uk.ac.glasgow.jagora.test.impl;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.impl.DefaultTrader;
import uk.ac.glasgow.jagora.impl.LimitSellOrder;
import uk.ac.glasgow.jagora.test.SellOrderTest;

public class LimitSellOrderTest extends SellOrderTest {
	

	@Before 
	public void setUp (){
		Trader seller = new DefaultTrader("default", 500.0, lemons, 10);
		sellOrder0 = new LimitSellOrder (seller, lemons, 1, 0.5);
		sellOrder1 = new LimitSellOrder (seller, lemons, 1, 1.0);
		order0 = sellOrder0;
		order1 = sellOrder1;
		sameAsOrder1 = new LimitSellOrder (seller, lemons, 1, 1.0);
		
		nullPriceOrder1 = new LimitSellOrder (seller, lemons, 1, null);
		nullPriceOrder2 = new LimitSellOrder (seller, lemons, 1, null);
	}
	
	@Test
	public void testGetPrice() {
		assertEquals("", 0.1, sellOrder0.getPrice(), 0.5);
	}
}
