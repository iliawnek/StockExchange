package uk.ac.glasgow.jagora.test.impl;

import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.Before;

import uk.ac.glasgow.jagora.impl.DefaultStockExchange;
import uk.ac.glasgow.jagora.impl.DefaultTrader;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.impl.LimitSellOrder;
import uk.ac.glasgow.jagora.test.StockExchangeAndTraderTest;

public class DefaultStockExchangeAndTraderTest extends StockExchangeAndTraderTest {
	
	@Before
	public void setUp(){
		stockExchange = new DefaultStockExchange(new DefaultWorld());
		
		buyer = new DefaultTrader("buyer", 1000.0, lemons, 0);
		seller = new DefaultTrader("seller", 0.0, lemons, 10);
		
		
		goodSellOrder =
				new LimitSellOrder(seller, lemons, 1, buyer.getCash()+0.01);
		badBuyOrder =
				new LimitBuyOrder(buyer, lemons, 1, buyer.getCash()+0.01);
		

		goodBuyOrder =
				new LimitBuyOrder(buyer, lemons, seller.getInventoryHolding(lemons)+1, 10.0);
		badSellOrder =
				new LimitSellOrder(seller, lemons, seller.getInventoryHolding(lemons)+1, 0.00);
	}
}
