package uk.ac.glasgow.jagora;

/**
 * Provides the behaviour for managing timed events in a stock exchange world.
 * Implementations of world should guarantee uniqueness of ticks.
 */
public interface World {
	
	/**
	 * @param event
	 * @return an event with an associated tick for the specified event.
	 */
	public <T> TickEvent<T> createTickEvent(T event);
}
