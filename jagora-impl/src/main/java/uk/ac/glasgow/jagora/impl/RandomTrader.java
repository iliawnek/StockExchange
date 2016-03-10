package uk.ac.glasgow.jagora.impl;

import java.util.*;

import uk.ac.glasgow.jagora.*;

/**
 * Implements a random trading strategy using limit orders. The trader will
 * place random buy and sell orders within a constrained price range (and up to
 * a maximum quantity) of the current market spread (bestBid, bestOffer). The
 * pseudo code for the trading strategy is as follows:
 * <p>
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
 */
public class RandomTrader implements Trader {

    private String name;
    private Double cash;
    private HashMap<Stock, Integer> inventory = new HashMap<>();
    private Integer maxTradeQuantity;
    private Double priceRange;
    private Random random;

    public RandomTrader(
            String name, Double cash, Stock stock, Integer quantity,
            Integer maxTradeQuantity, double priceRange, Random random) {
		this.name = name;
        this.cash = cash;
        this.inventory.put(stock, quantity);
        this.maxTradeQuantity = maxTradeQuantity;
        this.priceRange = priceRange;
        this.random = random;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCash() {
        return cash;
    }

    @Override
    public void sellStock(Stock stock, Integer quantity, Double price) throws TradeException {
        if (inventory.containsKey(stock)) {
            int amount = inventory.get(stock);
            if (amount - quantity >= 0) {
                cash += quantity * price;
                inventory.replace(stock, amount - quantity);
            } else throw new TradeException("Seller not enough stock.", this);
        } else throw new TradeException("Seller not enough stock.", this);
    }

    @Override
    public void buyStock(Stock stock, Integer quantity, Double price)
            throws TradeException {
        if (cash - (quantity * price) >= 0) {
            cash -= quantity * price;
            if (inventory.containsKey(stock)) {
                inventory.replace(stock, inventory.get(stock) + quantity);
            } else inventory.put(stock, quantity);
        } else throw new TradeException("Buyer not enough cash.", this);

    }

    @Override
    public Integer getInventoryHolding(Stock stock) {
        if (inventory.containsKey(stock)) return inventory.get(stock);
        return 0;
    }

    @Override
    public void speak(StockExchange stockExchange) {
        boolean buy = random.nextBoolean();
        double price = (random.nextDouble() - 0.5) * priceRange;
        int quantity = random.nextInt(maxTradeQuantity - 1) + 1;

        List<Stock> keys = new ArrayList<>(inventory.keySet());
        Stock stock = keys.get(random.nextInt(keys.size()));

        if (buy) {
            price += stockExchange.getBestBid(stock);
            BuyOrder buyOrder = new LimitBuyOrder(this, stock, quantity, price);
            stockExchange.placeBuyOrder(buyOrder);
        }
        else {
            price += stockExchange.getBestOffer(stock);
            SellOrder sellOrder = new LimitSellOrder(this, stock, quantity, price);
            stockExchange.placeSellOrder(sellOrder);
        }

    }

    @Override
    public Set<Stock> getTradingStocks() {
        Set<Stock> stockSet = inventory.keySet();
        return stockSet;
    }

}
