package uk.ac.glasgow.jagora.test.impl;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.buyer;
import static uk.ac.glasgow.jagora.test.stub.StubTrader.seller;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.impl.ContinuousOrderDrivenMarket;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.impl.LimitBuyOrder;
import uk.ac.glasgow.jagora.impl.LimitSellOrder;
import uk.ac.glasgow.jagora.test.MarketTest;

@RunWith(Enclosed.class)
public class ContinuousOrderDrivenMarketTest  {
		
	public static class TestAsMarket extends MarketTest {
	
		@Before
		public void setUp() {
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
			
			market = new ContinuousOrderDrivenMarket(lemons, new DefaultWorld());
		}
	}


}
