package uk.ac.glasgow.jagora.impl;

import java.util.Random;
import java.util.Set;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TradeException;
import uk.ac.glasgow.jagora.Trader;

/**
 * Implements a random trading strategy using limit orders. The trader will
 * place random buy and sell orders within a constrained price range (and up to
 * a maximum quantity) of the current market spread (bestBid, bestOffer). The
 * pseudo code for the trading strategy is as follows:
 * 
 * <pre>
 * buy <- randomChoice
 * if buy:
 *   price <- bestBid + (-0.5 <= random <= 0.5) * priceRange
 *   quantity <- (0 < random < maxTradeQuantity)
 *   stock <- random (s in inventory)
 *   placeLimitBuyOrder(stock, quantity, price)
 * else:
 *   price <- bestOffer + (-0.5 <= random <= 0.5) * priceRange
 *   quantity <- (0 < random < maxTradeQuantity)
 *   stock <- random (s in inventory)
 *   placeLimitSellOrder(stock, quantity, price)
 * </pre>
 * 
 * @author tws
 *
 */
public class RandomTrader implements Trader {

	public RandomTrader(
		String name, Double cash, Stock stock, Integer quantity,
		Integer maxTradeQuantity, double priceRange, Random random) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getCash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sellStock(Stock stock, Integer quantity, Double price)
			throws TradeException {
		// TODO Auto-generated method stub

	}

	@Override
	public void buyStock(Stock stock, Integer quantity, Double price)
			throws TradeException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getInventoryHolding(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void speak(StockExchange stockExchange) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Stock> getTradingStocks() {
		// TODO Auto-generated method stub
		return null;
	}

}
