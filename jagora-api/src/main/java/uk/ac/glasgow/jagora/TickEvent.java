package uk.ac.glasgow.jagora;

/**
 * Provides a general interface for denoting timed events.
 * @author tws
 *
 * @param <T> the event type associated with the specified tick.
 */
public interface TickEvent<T> extends Comparable<TickEvent<T>> {
	
	/**
	 * @return the object providing the detail for the event that occurred at
	 *         the tick time.
	 */
	public T getEvent();
	
	public Long getTick();

}
