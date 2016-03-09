package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.tradeEvent;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.Order;
import uk.ac.glasgow.jagora.Trade;

/**
 * Tests the abstract behaviour of any order class.
 * @author tws
 *
 */

@Ignore
public abstract class OrderTest {
	
	protected Order order0;
	protected Order order1;
	protected Order sameAsOrder1;
	
	protected Order nullPriceOrder1;
	protected Order nullPriceOrder2;
	
	@Test
	public void testSatisfyTrade() throws Exception {		
		Trade trade = tradeEvent.getEvent();
		
		Integer initialOrderQuantity = order0.getRemainingQuantity();
		order0.satisfyTrade(tradeEvent);
		Integer remainingOrderQuantity = order0.getRemainingQuantity();
		
		assertEquals("", initialOrderQuantity - trade.getQuantity(), remainingOrderQuantity.intValue());
	}
	
	@Test
	public void testRollBackTrade() throws Exception {
		
		Integer initialQuantity = order0.getRemainingQuantity();
		order0.satisfyTrade(tradeEvent);
		order0.rollBackTrade(tradeEvent);
		Integer finalQuantity = order0.getRemainingQuantity();
		
		assertEquals("", initialQuantity.intValue(), finalQuantity.intValue());
	}
	
	@Test
	public void testIsEquals (){
		assertTrue(order0.equals(order0));
	}
	
	@Test
	public void testNotEqualsNull (){
		assertFalse(order0.equals(null));
	}
	
	@Test
	public void testNotEqualsOrder (){
		assertFalse( order1.equals(order0));
	}

	@Test
	public void testNotEqualsDifferentTypes(){
		assertFalse( order0.equals("Order"));
	}
	
	@Test
	public void testDifferentOrdersSamePrice (){
		assertTrue(order1.equals(sameAsOrder1));
	}
	
	@Test
	public void testNullPriceEquals (){
		assertTrue(nullPriceOrder1.equals(nullPriceOrder2));
	}
	
}
