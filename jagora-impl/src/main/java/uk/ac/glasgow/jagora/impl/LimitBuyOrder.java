package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.*;

public class LimitBuyOrder implements BuyOrder {
	
	private Trader trader;
	private Stock stock;
	private Integer quantity;
	private Double price;

	public LimitBuyOrder(Trader trader, Stock stock, Integer quantity, Double price) {
		this.trader = trader;
		this.stock = stock;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public Trader getTrader() {
		return trader;
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	/**
	 * @return the remaining quantity of stock still to be purchased.
     */
	@Override
	public Integer getRemainingQuantity() {
		return quantity;
	}

	/**
	 * Attempts to make a trade.
	 * @param tradeEvent to be attempted.
	 * @throws TradeException if the trade is unsuccessful.
     */
	@Override
	public void satisfyTrade(TickEvent<Trade> tradeEvent) throws TradeException {
		Double tradePrice = tradeEvent.getEvent().getPrice();
		Integer tradeQuantity = tradeEvent.getEvent().getQuantity();
		trader.buyStock(tradeEvent.getEvent().getStock(), tradeQuantity, tradePrice);
		quantity -= tradeQuantity;
	}

	/**
	 * Rolls back a previously initiated trade.
	 * @param tradeEvent to be rolled back.
	 * @throws TradeException if the roll back is unsuccessful.
     */
	@Override
	public void rollBackTrade(TickEvent<Trade> tradeEvent) throws TradeException {
		Double tradePrice = tradeEvent.getEvent().getPrice();
		Integer tradeQuantity = tradeEvent.getEvent().getQuantity();
		trader.sellStock(tradeEvent.getEvent().getStock(), tradeQuantity, tradePrice);
		quantity += tradeQuantity;
	}

    @Override
    public boolean equals(Object order) {
        if(order instanceof  Order) {
            Order o = (Order) order;
            if(o.getPrice() == null || price == null) return (o.getPrice() == price && o.getStock().getName().equals(stock.getName()) && o.getRemainingQuantity() == quantity);
            return (o.getPrice().equals(price) && o.getStock().getName().equals(stock.getName()) && o.getRemainingQuantity() == quantity);
        }else return false;
    }

	@Override
	public int compareTo(BuyOrder order) {
        if(price == null || order.getPrice() == null) return 0;
		return order.getPrice().compareTo(price);
	}
}
