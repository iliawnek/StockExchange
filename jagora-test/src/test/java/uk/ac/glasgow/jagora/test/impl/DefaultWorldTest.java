package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;

import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.test.WorldTest;

public class DefaultWorldTest extends WorldTest{

	@Before
	public void setUp() {
		world = new DefaultWorld();
	}

}
