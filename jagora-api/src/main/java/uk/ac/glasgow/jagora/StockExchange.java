package uk.ac.glasgow.jagora;

import java.util.List;

/**
 * Defines the behaviour of a stock exchange on which a variety of stocks can be
 * traded using different types of buy and sell orders.
 * 
 * @author tws
 *
 */
public interface StockExchange {

	/**
	 * Performs clearing on all stock markets hosted by this exchange. Clearing
	 * will proceed until no further trades are possible.
	 */
	public void doClearing ();

	/**
	 * Places a buy order on the stock exchange. If this is the first order for
	 * a stock then placing the order creates the market for that stock on the
	 * exchange.
	 * 
	 * @param buyOrder
	 */
	public void placeBuyOrder(BuyOrder buyOrder);
	
	/**
	 * Places a sell order on the stock exchange. If this is the first order for
	 * a stock then placing the order creates the market for that stock on the
	 * exchange.
	 * 
	 * @param buyOrder
	 */
	public void placeSellOrder(SellOrder sellOrder);
	
	public void cancelBuyOrder(BuyOrder buyOrder);
	
	public void cancelSellOrder(SellOrder sellOrder);
	
	/**
	 * 
	 * @param stock
	 * @return an ordered (by execution) list of tick events for the trades that
	 *         have occurred since initiation for the specified stock on this
	 *         exchange.
	 */
	public List<TickEvent<Trade>> getTradeHistory(Stock stock);
	
	/**
	 * @param stock
	 * @return the lowest priced offer for the specified stock on this exchange,
	 *         or null if no offer exists or the exchange does not trade the
	 *         specified stock.
	 */
	public Double getBestOffer(Stock stock);
	
	/**
	 * 
	 * @param stock
	 * @return the highest priced bid for the specified stock on this exchange,
	 *         or null if no bid exists or the exchange does not trade the
	 *         specified stock.
	 */
	public Double getBestBid(Stock stock);
}
