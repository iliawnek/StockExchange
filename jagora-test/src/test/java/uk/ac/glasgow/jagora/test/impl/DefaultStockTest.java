package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;

import uk.ac.glasgow.jagora.impl.DefaultStock;
import uk.ac.glasgow.jagora.test.StockTest;

public class DefaultStockTest extends StockTest {

	@Before
	public void setUp() throws Exception {
		this.stock = new DefaultStock("lemons");
	}

}
