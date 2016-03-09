package uk.ac.glasgow.jagora.test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.expectedPrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

@Ignore
public abstract class MarketTest {
			
	protected Market market;
	protected List<BuyOrder> buyOrders;
	protected List<SellOrder> sellOrders;

	@Test
	public void testGetStock() {
		assertEquals("", lemons, market.getStock());
	}
	
	@Test
	public void testPlaceBuyOrder() {
		BuyOrder buyOrder = buyOrders.get(0);
		market.placeBuyOrder(buyOrder);
		assertEquals(buyOrder.getPrice(), market.getBestBid(), 0.0);
	}

	@Test
	public void testPlaceSellOrder() {
		SellOrder sellOrder = sellOrders.get(0);
		market.placeSellOrder(sellOrder);
		assertEquals(sellOrder.getPrice(), market.getBestOffer(), 0.0);
	}

	@Test
	public void testCancelBuyOrder() {
		BuyOrder buyOrder0 = buyOrders.get(0);
		BuyOrder buyOrder1 = buyOrders.get(1);		

		market.placeBuyOrder(buyOrder0);
		market.placeBuyOrder(buyOrder1);
		market.cancelBuyOrder(buyOrder0);
		assertEquals(buyOrder1.getPrice(), market.getBestBid());
	}

	@Test
	public void testCancelSellOrder() {
		SellOrder sellOrder0 = sellOrders.get(0);
		SellOrder sellOrder1 = sellOrders.get(1);		

		market.placeSellOrder(sellOrder0);
		market.placeSellOrder(sellOrder1);
		market.cancelSellOrder(sellOrder0);
		assertEquals(sellOrder1.getPrice(), market.getBestOffer());
	}

	@Test
	public void testGetBestBid() {
		
		List<BuyOrder> shuffled = new ArrayList<BuyOrder>(buyOrders);
		Collections.shuffle(shuffled);
		placeBuyOrders(shuffled);
				
		for (BuyOrder buyOrder: buyOrders){
			assertEquals("", buyOrder.getPrice(), market.getBestBid(), 0.0);
			market.cancelBuyOrder(buyOrder);
		}
	}

	@Test
	public void testGetBestOffer() {
			
		List<SellOrder> shuffled = new ArrayList<SellOrder>(sellOrders);
		Collections.shuffle(shuffled);
		placeSellOrders(shuffled);
						
		for (SellOrder sellOrder: sellOrders){
			assertEquals("", sellOrder.getPrice(), market.getBestOffer(), 0.0);
			market.cancelSellOrder(sellOrder);
		}
	}
		
	@Test
	public void testDoClearing() {
		placeBuyOrders(buyOrders);
		placeSellOrders(sellOrders);
	
		List<TickEvent<Trade>> tickEvents = market.doClearing();
		assertEquals(expectedPrices.length, tickEvents.size());
		
		Integer numberOfOrderTicks =
			buyOrders.size()+sellOrders.size();
		
		for (Integer i = 0; i < tickEvents.size(); i++){
			TickEvent<Trade> tickEvent = tickEvents.get(i);
			Trade trade = tickEvent.getEvent();
			
			assertEquals(format("%s", trade), expectedPrices[i], trade.getPrice(), 0.0);
			assertEquals(format("%s",trade), 1, trade.getQuantity().intValue());
			assertEquals(format("%s",tickEvent), i+numberOfOrderTicks, tickEvent.getTick().intValue());
		}
		
	}

	private void placeSellOrders(List<SellOrder> sellOrders) {
		sellOrders
			.stream()
			.forEach(sellOrder->market.placeSellOrder(sellOrder));
	}
	
	private void placeBuyOrders(List<BuyOrder> buyOrders) {
		buyOrders
			.stream()
			.forEach(buyOrder->market.placeBuyOrder(buyOrder));
	}
}
