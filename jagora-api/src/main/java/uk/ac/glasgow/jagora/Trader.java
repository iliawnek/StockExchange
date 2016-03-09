package uk.ac.glasgow.jagora;

import java.util.Set;

/**
 * Defines the behaviour of a trader who places and cancels orders on a stock
 * exchange.
 * 
 * @author tws
 *
 */
public interface Trader {
	
	public String getName();
	
	/**
	 * @return this Trader's current cash balance. A trader cannot have less
	 *         than 0 cash.
	 */
	public Double getCash();
	
	/**
	 * Decrements the trader's inventory of the specified stock by the specified
	 * quantity and credit's their cash balance with the unit price multiplied by
	 * the quantity.
	 * 
	 * @param stock
	 * @param quantity
	 *            the number of stock units to be sold.
	 * @param price
	 *            the unit price for the stock sale.
	 * @throws TradeException
	 *             if the Trader does not hold the necessary quantity of the
	 *             specified stock.
	 */
	public void sellStock(Stock stock, Integer quantity, Double price) throws TradeException;
	
	/**
	 * Increments the trader's inventory of the specified stock by the specified
	 * quantity and debit's their cash balance with the unit price multipled by
	 * the quantity.
	 * 
	 * @param stock
	 * @param quantity
	 *            the number of stock units to be bought.
	 * @param price
	 *            the unit price of the stock purchase.
	 * @throws TradeException
	 *             if the Trader does not have sufficient cash to meet the price
	 *             of the purchase.
	 */
	public void buyStock(Stock stock, Integer quantity, Double price) throws TradeException;
	
	/**
	 * @param stock
	 * @return the current quantity of the specified stock in the Trader's
	 *         inventory. Quantity cannot be less than zero.
	 */
	public Integer getInventoryHolding(Stock stock);
	
	/**
	 * Presents the Trader with an opportunity to place or cancel orders on a
	 * stock exchange. Implementations of stock exchange may limit the window of
	 * opportunity for a trader to speak once the speak operation has been
	 * invoked.
	 * 
	 * @param stockExchange
	 */
	public void speak(StockExchange stockExchange);
	
	/**
	 * @return the set of stock types that this Trader has on inventory.
	 */
	public Set<Stock> getTradingStocks();
}
