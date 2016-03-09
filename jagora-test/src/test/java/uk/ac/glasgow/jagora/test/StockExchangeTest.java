package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.*;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.expectedPrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

/**
 * Defines tests for implementations of the stock exchange interface.
 * @author Tim
 *
 */
@Ignore
public abstract class StockExchangeTest {

	protected StockExchange stockExchange;
	
	protected List<BuyOrder> buyOrders;
	protected List<SellOrder> sellOrders;


	@Test
	public void testDoClearing() {
		placeStandardOrdersAndClear();
		assertEquals("", null, stockExchange.getBestBid(lemons));
		assertEquals("", 2.5, stockExchange.getBestOffer(lemons), 0.0);
	}

	@Test
	public void testPlaceBuyOrder() {
		BuyOrder buyOrder = buyOrders.get(0);
		stockExchange.placeBuyOrder(buyOrder);
		assertEquals(buyOrder.getPrice(), stockExchange.getBestBid(buyOrder.getStock()), 0.0);
	}

	@Test
	public void testPlaceSellOrder() {
		SellOrder sellOrder = sellOrders.get(0);
		stockExchange.placeSellOrder(sellOrder);
		assertEquals(sellOrder.getPrice(), stockExchange.getBestOffer(sellOrder.getStock()), 0.0);
	}

	@Test
	public void testCancelBuyOrder() {
		stockExchange.placeBuyOrder(buyOrders.get(0));
		stockExchange.placeBuyOrder(buyOrders.get(1));
		stockExchange.cancelBuyOrder(buyOrders.get(0));
		assertEquals(buyOrders.get(1).getPrice(), stockExchange.getBestBid(buyOrders.get(1).getStock()), 0.0);
	}

	@Test
	public void testCancelSellOrder() {
		stockExchange.placeSellOrder(sellOrders.get(0));
		stockExchange.placeSellOrder(sellOrders.get(1));
		stockExchange.cancelSellOrder(sellOrders.get(0));
		assertEquals(sellOrders.get(1).getPrice(), stockExchange.getBestOffer(sellOrders.get(1).getStock()), 0.0);
	}

	@Test
	public void testGetBestOffer() {
		List<SellOrder> shuffled = new ArrayList<SellOrder>(sellOrders);
		Collections.shuffle(shuffled);
		
		placeSellOrders(shuffled);
				
		for (SellOrder sellOrder : sellOrders){
			Double bestBid = stockExchange.getBestOffer(lemons);
			assertEquals(sellOrder.getPrice(), bestBid, 0.0);
			stockExchange.cancelSellOrder(sellOrder);
		}
	}

	@Test
	public void testGetBestBid() {
		List<BuyOrder> shuffled = new ArrayList<BuyOrder>(buyOrders);
		Collections.shuffle(shuffled);
		
		placeBuyOrders(shuffled);
				
		for (BuyOrder buyOrder : buyOrders){
			Double bestBid = stockExchange.getBestBid(lemons);
			assertEquals(buyOrder.getPrice(), bestBid, 0.0);
			stockExchange.cancelBuyOrder(buyOrder);
		}
	}
	
	@Test
	public void testGetTradeHistory() {
		placeStandardOrdersAndClear();
		List<TickEvent<Trade>> tradeHistory =
				stockExchange.getTradeHistory(lemons);
			
		assertEquals("", expectedPrices.length, tradeHistory.size());

		for (int i = 0; i > tradeHistory.size(); i++){
			Double actualPrice = tradeHistory.get(i).getEvent().getPrice();
			assertEquals ("", expectedPrices[i], actualPrice);
		}	
	}
	
	private void placeStandardOrdersAndClear() {
		placeSellOrders (sellOrders);
		placeBuyOrders (buyOrders);
		stockExchange.doClearing();
	}
	
	private void placeBuyOrders(List<BuyOrder> buyOrders) {
		for (BuyOrder buyOrder : buyOrders)
			stockExchange.placeBuyOrder(buyOrder);
	}
	
	private void placeSellOrders(List<SellOrder> buyOrders) {
		for (SellOrder buyOrder : buyOrders)
			stockExchange.placeSellOrder(buyOrder);
	}

}
