package uk.ac.glasgow.jagora;

/**
 * Defines the general behaviour for a proposed trade between one buy and one
 * sell order. Invoking the execute operation causes the trade to be applied to
 * the trade's buy and sell orders.
 * 
 * @author tws
 *
 */
public interface Trade {
	
	public Stock getStock();
	
	/**
	 * @return the quantity of stocks that should be traded.
	 */
	public Integer getQuantity();
	
	/**
	 * @return the unit price for stock at which the trade proposed trade will occur.
	 */
	public Double getPrice();
	
	/**
	 * 
	 * @return a tick event recording the time that the trade was completed.
	 * @throws TradeException
	 *             if the application of the proposed trade was invalid for
	 *             either the buy or sell order; or if the trade could not be
	 *             satisfied by the associated buying or selling traders.
	 */
	public TickEvent<Trade> execute() throws TradeException;
}
