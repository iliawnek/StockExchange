package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

/**
 * Defines tests for the common behaviour of implementations of the Trader
 * interface. Specifically tests that the trader correctly satisfies buy and
 * sell obligations resulting from trades.
 * 
 * @author tws
 *
 */
@Ignore
public abstract class TraderTest {

	protected Stock stock;
	protected Integer quantity;
	protected Double cash;
	protected String name;
	
	protected Trader trader;

	@Test
	public void testGetName (){
		assertEquals(name,trader.getName());
	}
	
	@Test
	public void testGetCash (){
		assertEquals(cash, trader.getCash(), 0.0);
	}

	@Test
	public void testGetInventoryHolding (){
		assertEquals(quantity.intValue(), trader.getInventoryHolding(stock).intValue());
	}
	
	@Test
	public void testGetTradingStocks (){
		Set<Stock> expected = new HashSet<Stock>();
		expected.add(stock);
		assertEquals(expected, trader.getTradingStocks());
	}
	
	@Test
	public void testBuyStock() throws Exception {
		Double initialCash = trader.getCash();
		Integer initialQuantity = trader.getInventoryHolding(stock);
		trader.buyStock(stock, 10, 50.0);
		assertEquals(initialCash - 500 , trader.getCash(), 0.0);
		assertEquals(initialQuantity + 10, trader.getInventoryHolding(stock).intValue());
	}
	
	@Test
	public void testSellStock() throws Exception {
		Double initialCash = trader.getCash();
		Integer initialQuantity = trader.getInventoryHolding(stock);
		trader.sellStock(stock, 10, 50.0);
		assertEquals(initialCash + 500.0, trader.getCash(), 0.0);
		assertEquals(initialQuantity - 10, trader.getInventoryHolding(stock).intValue());
	}
	
	@Test(expected=TradeException.class)
	public void testBadBuyStock()  throws Exception {
		trader.buyStock(stock, 2, cash);
	}
	
	@Test(expected=TradeException.class)
	public void testBadBuyStockAtLimit()  throws Exception {
		trader.buyStock(stock, 1, cash+0.01);
	}
	
	@Test(expected=TradeException.class)
	public void testBadSellStock() throws Exception {
		trader.sellStock(stock, quantity+1, 1.0);
	}

}
