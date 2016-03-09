package uk.ac.glasgow.jagora.test.impl;

import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.seller;

import org.junit.Before;

import uk.ac.glasgow.jagora.impl.DefaultTrade;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.impl.LimitSellOrder;
import uk.ac.glasgow.jagora.test.TradeTest;
import uk.ac.glasgow.jagora.test.stub.StubTrader;

public class DefaultTradeTest extends TradeTest {

	@Before
	public void setUp() throws Exception {
		buyOrder = new LimitBuyOrder(StubTrader.buyer, lemons, quantity, price);
		sellOrder = new LimitSellOrder(seller, lemons, quantity, price);
	
		trade = new DefaultTrade(
			new DefaultWorld(), buyOrder, sellOrder, lemons, quantity, price);
	}

}
