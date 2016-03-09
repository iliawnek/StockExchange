package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.TickEvent;

public class DefaultTickEvent<T> implements TickEvent<T> {

	private T event;
	private Long tick;
	
	public DefaultTickEvent(T event, Long tick) {
		this.event = event;
		this.tick = tick;
	}

	@Override
	public int compareTo(TickEvent<T> tickEvent) {
		return tick.compareTo(tickEvent.getTick());
	}

	@Override
	public T getEvent() {
		return event;
	}

	@Override
	public Long getTick() {
		return tick;
	}

	public String toString(){
		return "Tick: " + tick + "\nEvent: " + event;
	}
}
