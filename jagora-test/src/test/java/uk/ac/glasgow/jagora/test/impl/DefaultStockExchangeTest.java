package uk.ac.glasgow.jagora.test.impl;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.buyer;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.seller;

import org.junit.Before;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.impl.DefaultStockExchange;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.test.StockExchangeTest;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.impl.LimitSellOrder;

public class DefaultStockExchangeTest extends StockExchangeTest {

	@Before
	public void setUp() throws Exception {
		stockExchange = new DefaultStockExchange(new DefaultWorld());
		buyOrders = 
			unmodifiableList(asList(new BuyOrder[]{
				new LimitBuyOrder(buyer, lemons, 1, 2.5),
				new LimitBuyOrder(buyer, lemons, 1, 1.0),
				new LimitBuyOrder(buyer, lemons, 1, 0.5),
				new LimitBuyOrder(buyer, lemons, 1, 0.5)}));
		
		sellOrders = 
			unmodifiableList(asList(new SellOrder[]{
				new LimitSellOrder(seller, lemons, 2, 0.2),
				new LimitSellOrder(seller, lemons, 1, 0.5),
				new LimitSellOrder(seller, lemons, 1, 0.5),
				new LimitSellOrder(seller, lemons, 1, 2.5)}));

	}

}
