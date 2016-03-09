package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.glasgow.jagora.Stock;

public abstract class StockTest {

	protected Stock stock;

	@Test
	public void testGetName() {
		assertEquals("lemons",stock.getName());
	}
	
}
