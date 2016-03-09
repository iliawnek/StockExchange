package uk.ac.glasgow.jagora;

import java.util.List;

/**
 * Defines behaviour for trading in a single stock type.
 * @author tws
 *
 */
public interface Market {
	
	public Stock getStock();

	/**
	 * Performs clearing on the current buy and sell order books for this market
	 * by executing all possible trades. Clearing will proceed whilst the market
	 * is able to match compatible buy and sell orders.
	 * 
	 * @return an ordered (by execution) list of tick events for the trades that
	 *         occurred during this round of clearing.
	 */
	public List<TickEvent<Trade>> doClearing ();
	
	/**
	 * Enters the specified buy order onto the order book for this market. The
	 * order will persist on the market until it is filled during clearing or
	 * cancelled.
	 * 
	 * @param buyOrder
	 */
	public void placeBuyOrder(BuyOrder buyOrder);
	
	/**
	 * Enters the specified sell order onto the order book for this market. The
	 * order will persist on the market until it is filled during clearing or
	 * cancelled.
	 * 
	 * @param sellOrder
	 */
	public void placeSellOrder(SellOrder sellOrder);

	/**
	 * Removes the specified buy order (if present) from the order book for this
	 * market.
	 * 
	 * @param buyOrder
	 */
	public void cancelBuyOrder(BuyOrder buyOrder);
	
	/**
	 * Removes the specified sell order (if present) from the order book for this
	 * market.
	 * 
	 * @param sellOrder
	 */
	public void cancelSellOrder(SellOrder sellOrder);
	
	/**
	 * @return the price of the highest buy order currently on this market, or
	 *         null if the market has no bids.
	 */
	public Double getBestBid();
	
	/**
	 * @return the price of the lowest sell order currently on this market, or
	 *         null if the market has no offers.
	 */
	public Double getBestOffer();
}
