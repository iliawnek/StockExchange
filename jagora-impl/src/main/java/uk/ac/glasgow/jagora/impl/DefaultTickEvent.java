package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.TickEvent;

public class DefaultTickEvent<T> implements TickEvent<T> {

	private T event;
	private Long tick;
	
	public DefaultTickEvent(T event, Long tick) {

	}

	@Override
	public int compareTo(TickEvent<T> tickEvent) {
		//TODO
		return 0;
	}

	@Override
	public T getEvent() {
		//TODO
		return null;
	}

	@Override
	public Long getTick() {
		//TODO
		return null;
	}
	
	public String toString(){
		//TODO
		return null;
	}
}
