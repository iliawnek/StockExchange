package uk.ac.glasgow.jagora;

/**
 * Extends the definition of order behaviour for comparing sell orders.
 * @author tws
 *
 */
public interface SellOrder extends Order, Comparable<SellOrder> {

	/**
	 * Implements the compareTo operation for sell orders.
	 * 
	 * @return less than 0 if this sell order's price is strictly less than the
	 *         sell order argument's price; greater than 0 if this sell order's
	 *         price is more than the sell order argument's price and 0 if this
	 *         sell order's price and the sell order argument's price are equal.
	 */
		public int compareTo(SellOrder order) ;

}
