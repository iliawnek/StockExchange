package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.impl.DefaultOrderBook;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.test.OrderBookTest;

public class DefaultOrderBookTest extends OrderBookTest {

	@Before
	public void setUp() {
		orderBook = new DefaultOrderBook<BuyOrder>(new DefaultWorld());
	}

}
