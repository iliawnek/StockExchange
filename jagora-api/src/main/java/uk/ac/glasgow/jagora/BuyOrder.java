package uk.ac.glasgow.jagora;

/**
 * Extends the definition of order behaviour for comparing buy orders.
 * @author tws
 *
 */
public interface BuyOrder extends Order, Comparable<BuyOrder> {

	/**
	 * Implements the compareTo operation for buy orders.
	 * 
	 * @return less than 0 if this buy order's price is strictly more than the
	 *         buy order argument's price; greater than 0 if this buy order's
	 *         price is less than the buy order argument's price and 0 if this
	 *         buy order's price and the buy order argument's price are equal.
	 */
	public int compareTo(BuyOrder order);

}