package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.World;

public class DefaultWorld implements World {

	private Long tickCount = 0l;	

	@Override
	public <T> TickEvent<T> createTickEvent(T event) {
		return new DefaultTickEvent<T>(event, ++tickCount);
	}

}
