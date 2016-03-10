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
	 * @param stock
	 * @param world
	 */
	public ContinuousOrderDrivenMarket(Stock stock, World world) {
		this.stock = stock;
		this.world = world;
		sellBook = new DefaultOrderBook<>(world);
		buyBook = new DefaultOrderBook<>(world);
	}

	@Override
	public Stock getStock() {
		return stock;
	}

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

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		buyBook.recordOrder(buyOrder);
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		sellBook.recordOrder(sellOrder);
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		buyBook.cancelOrder(buyOrder);
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		sellBook.cancelOrder(sellOrder);
	}

	@Override
	public Double getBestBid() {
		Order order = buyBook.getBestOrder();
		if (order == null) return null;
		return order.getPrice();
	}

	@Override
	public Double getBestOffer() {
		Order order = sellBook.getBestOrder();
		if (order == null) return null;
		return order.getPrice();
	}
	
	@Override
	public String toString(){
		return String.format("%s market\nbuy orders: %s\nsell orders: %s", stock, buyBook, sellBook);
	}
}
