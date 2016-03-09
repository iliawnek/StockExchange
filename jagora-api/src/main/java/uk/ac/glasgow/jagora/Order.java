package uk.ac.glasgow.jagora;

/**
 * Defines operations for orders on a stock exchange.
 * @author tws
 *
 */
public interface Order {
	
	public Trader getTrader();
	
	public Stock getStock();
	
	/**
	 * @return the remaining quantity of stock required to fill this trade.
	 */
	public Integer getRemainingQuantity();
	
	/**
	 * @return the quoted price for this order.
	 */
	public Double getPrice();
	
	/**
	 * Applies a trade to this order and to the underlying trader.
	 * @param tradeEvent
	 * @throws TradeException
	 *             if the trade parameters are unacceptable to this trade, or
	 *             the trader who made the trade cannot satisfy the demand.
	 */
	public void satisfyTrade(TickEvent<Trade> tradeEvent) throws TradeException;
	
	/**
	 * Reverts the effects of a trade on this order.
	 * 
	 * @param tradeEvent
	 * @throws TradeException
	 *             if the trade parameters are unacceptable to this trade, or
	 *             the trader who made the trade cannot satisfy the demand.
	 */
	public void rollBackTrade(TickEvent<Trade> tradeEvent) throws TradeException;
	
}
