package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;
import static uk.ac.glasgow.jagora.test.stub.StubTrade.trade;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;

@Ignore
public abstract class WorldTest {
	
	protected World world;

	@Test
	public void test() {
		TickEvent<Trade> tradeEvent = 
			world.createTickEvent(trade);
		
		assertEquals(trade, tradeEvent.getEvent());
	}

}
