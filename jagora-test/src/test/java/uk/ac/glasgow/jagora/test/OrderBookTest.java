package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubBuyOrder.stubBuyOrders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.OrderBook;
import uk.ac.glasgow.jagora.TickEvent;

/**
 * Defines test for implementations of the OrderBook interface.
 * @author tws
 *
 */
@Ignore
public abstract class OrderBookTest {
	
	protected OrderBook<BuyOrder> orderBook;
			
	@Test
	public void testEmptyInitialOrderBook (){
		assertEquals("", 0, orderBook.getOrdersAsList().size());
	}

	@Test
	public void testGetOrdersAsList() {
		List<BuyOrder> shuffled = new ArrayList<BuyOrder>(stubBuyOrders);
		Collections.shuffle(shuffled);
		recordOrders(shuffled);
				
		List<TickEvent<BuyOrder>> tickEvents =
				orderBook.getOrdersAsList();
					
		for (Integer i = 0; i < stubBuyOrders.size(); i ++){
			BuyOrder actual = tickEvents.get(i).getEvent();
			BuyOrder expected = stubBuyOrders.get(i);
			assertEquals(expected.getPrice(), actual.getPrice(), 0.0);
			assertEquals(expected.getRemainingQuantity(), actual.getRemainingQuantity());
		}
	}

	@Test
	public void testRecordOrder() {
		BuyOrder buyOrder = stubBuyOrders.get(0);
		orderBook.recordOrder(buyOrder);
		List<TickEvent<BuyOrder>> tickEvents = orderBook.getOrdersAsList();
		assertEquals(buyOrder, tickEvents.get(0).getEvent());
	}

	@Test
	public void testCancelOrder() {
		BuyOrder buyOrder = stubBuyOrders.get(0);
		orderBook.recordOrder(buyOrder);
		orderBook.cancelOrder(buyOrder);
		assertEquals(0, orderBook.getOrdersAsList().size());
	}

	@Test
	public void testGetBestOrder() {
		
		List<BuyOrder> shuffled = new ArrayList<BuyOrder>(stubBuyOrders);
		Collections.shuffle(shuffled);
		recordOrders(shuffled);
		
		for (BuyOrder buyOrder : stubBuyOrders){
			assertEquals("", buyOrder, orderBook.getBestOrder());	
			orderBook.cancelOrder(buyOrder);
		}
	}

	private void recordOrders(List<BuyOrder> orders) {
		orders
			.stream()
			.forEach(buyOrder->orderBook.recordOrder(buyOrder));
	}

}
