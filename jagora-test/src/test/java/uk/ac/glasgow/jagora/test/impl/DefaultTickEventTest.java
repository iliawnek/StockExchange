package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;

import uk.ac.glasgow.jagora.impl.DefaultTickEvent;
import uk.ac.glasgow.jagora.test.TickEventTest;

public class DefaultTickEventTest extends TickEventTest<String> {

	@Before
	public void setUp() {
		tickEvent0 = new DefaultTickEvent<String>("a", 0l);
		tickEvent1 = new DefaultTickEvent<String>("b", 1l);
	}

}
