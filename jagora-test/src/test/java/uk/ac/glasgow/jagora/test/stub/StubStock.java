package uk.ac.glasgow.jagora.test.stub;

import uk.ac.glasgow.jagora.Stock;

public class StubStock implements Stock {
	
	public static final Stock lemons = new StubStock ();
	
	@Override
	public String getName() {
		return "lemons";
	}

}
