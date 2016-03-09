package uk.ac.glasgow.jagora.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.TickEvent;

/**
 * Defines tests for implementations of the tick event interface.
 * @author tws
 *
 * @param <T> the type of tick event to be tested.
 */
@Ignore
public abstract class TickEventTest<T> {

	protected TickEvent<T> tickEvent0;
	protected TickEvent<T> tickEvent1;

	@Test
	public void testCompareToLessThan() {
		assertThat(tickEvent0.compareTo(tickEvent1), lessThan(0));
	}
	
	@Test
	public void testCompareToGreaterThan() {
		assertThat(tickEvent1.compareTo(tickEvent0), greaterThan(0));
	}
	
	@Test
	public void testCompareToEquals() {
		assertEquals(0, tickEvent0.compareTo(tickEvent0));
	}

	@Test
	public void testGetEvent() {
		assertNotNull(tickEvent0.getEvent());
	}

	@Test
	public void testGetTick() {
		assertEquals(0l, tickEvent0.getTick().longValue());
	}

}
