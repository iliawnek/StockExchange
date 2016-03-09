package uk.ac.glasgow.jagora;

import java.util.List;

/**
 * Defines the general behaviours for managing stock market orders in an order book.
 * @author tws
 *
 * @param <O> the abstract order type managed by this order book (either buy or sell orders).
 */
public interface OrderBook<O extends Order & Comparable<O>>  {

	public void recordOrder(O order);
		
	public void cancelOrder(O order);
	
	/**
	 * @return the best available order recorded in this order book. Orders are
	 *         prioritised by price (high to low for bids and low to high for
	 *         offers) and then by time received.
	 */
	public O getBestOrder();

	/**
	 * @return the complete list of orders recorded in this order book sorted by
	 *         price and time.
	 */
	public List<TickEvent<O>> getOrdersAsList();
}
