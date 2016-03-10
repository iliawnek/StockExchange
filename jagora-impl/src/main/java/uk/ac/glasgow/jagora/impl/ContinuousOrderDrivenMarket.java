package uk.ac.glasgow.jagora.impl;


import java.util.ArrayList;
import java.util.List;

import uk.ac.glasgow.jagora.*;

/**
 * Provides the behaviour of a continuous order driven market.
 * @author tws
 *
 */
public class ContinuousOrderDrivenMarket implements Market {
	
	private OrderBook<SellOrder> sellBook;
	private OrderBook<BuyOrder> buyBook;
	private Stock stock;
	private World world;
	
	/**
	 * Constructs a new continuous order driven market for the specified stock,
	 * synchronised to the tick events of the specified world.
	 * 
	 * @param stock to be traded in the market.
	 * @param world which controls the market's timing events.
	 */
	public ContinuousOrderDrivenMarket(Stock stock, World world) {
		this.stock = stock;
		this.world = world;
		sellBook = new DefaultOrderBook<>(world);
		buyBook = new DefaultOrderBook<>(world);
	}

	/**
	 * @return the stock being traded in this market.
     */
	@Override
	public Stock getStock() {
		return stock;
	}

	/**
	 * Continually matches the best bid and offer until no more trades can occur.
	 * No more trades occur when:
	 * 		The buy or sell order book is empty.
	 * 		The best bid cannot satisfy the best offer.
	 * Cancels bids/offers which cannot be fulfilled by the associated trader (not enough cash/stock quantity).
	 * Cancels bids/offers which have been fulfilled by a successful trade.
	 * @return list of trade tick events which occurred during the clearing process.
     */
	@Override
	public List<TickEvent<Trade>> doClearing() {
		List<TickEvent<Trade>> executedTrades = new ArrayList<>();

		BuyOrder buyOrder;
		SellOrder sellOrder;

		while (true) {
			buyOrder = buyBook.getBestOrder();
			sellOrder = sellBook.getBestOrder();

			if (buyOrder == null) break;
			if (sellOrder == null) break;
			if (getBestBid() < getBestOffer()) break;

			double price = sellOrder.getPrice();
			int quantity = Math.min(buyOrder.getRemainingQuantity(), sellOrder.getRemainingQuantity());

			if (sellOrder.getRemainingQuantity() >
                    sellOrder.getTrader().getInventoryHolding(sellOrder.getStock())) {
                cancelSellOrder(sellOrder);
                continue;
            }

            if (buyOrder.getPrice() >
                    buyOrder.getTrader().getCash()) {
                cancelBuyOrder(buyOrder);
                continue;
            }

			Trade trade = new DefaultTrade(world, buyOrder, sellOrder, stock, quantity, price);
			try {
				executedTrades.add(trade.execute());
			}
			catch (TradeException e) {
				e.printStackTrace();
			}

			if (buyOrder.getRemainingQuantity() == 0) {
				cancelBuyOrder(buyOrder);
			}
			if (sellOrder.getRemainingQuantity() == 0) {
				cancelSellOrder(sellOrder);
			}
		}

		return executedTrades;
	}

	/**
	 * Adds a new buy order to this market's buy order book.
	 * @param buyOrder to be placed.
     */
	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		buyBook.recordOrder(buyOrder);
	}

	/**
	 * Adds a new sell order to this market's sell order book.
	 * @param sellOrder to be placed.
     */
	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		sellBook.recordOrder(sellOrder);
	}

	/**
	 * Removes a buy order from this market's buy order book.
	 * @param buyOrder to be cancelled.
     */
	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		buyBook.cancelOrder(buyOrder);
	}

	/**
	 * Removes a sell order from this market's sell order book.
	 * @param sellOrder to be cancelled.
     */
	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		sellBook.cancelOrder(sellOrder);
	}

	/**
	 * @return the buy order with the highest price.
     */
	@Override
	public Double getBestBid() {
		Order order = buyBook.getBestOrder();
		if (order == null) return null;
		return order.getPrice();
	}

	/**
	 * @return the sell order with the lowest price.
     */
	@Override
	public Double getBestOffer() {
		Order order = sellBook.getBestOrder();
		if (order == null) return null;
		return order.getPrice();
	}

	@Override
	public String toString(){
		return String.format("Stock: %s\nBuy orders: %s\nSell orders: %s", stock, buyBook, sellBook);
	}
}
