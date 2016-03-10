package uk.ac.glasgow.jagora.impl;

import java.util.HashMap;
import java.util.Set;

import uk.ac.glasgow.jagora.*;

/**
 * Implements a trader with behaviours for satisfying trades, but never speaks
 * on the exchange to place buy or sell orders.
 *
 * @author tws
 */
public class DefaultTrader implements Trader {

    private String name;
    private Double cash;
    private HashMap<Stock, Integer> inventory = new HashMap<>();

    /**
     * Constructs a new instance of default trader with the specified cash and a
     * single stock of the specified quantity.
     *
     * @param name of the trader.
     * @param cash: Quantity of cash belonging to the trader.
     * @param stock: The trader's initial stock type.
     * @param quantity of the trader's initial stock.
     */
    public DefaultTrader(String name, Double cash, Stock stock, Integer quantity) {
        this.name = name;
        this.cash = cash;
        this.inventory.put(stock, quantity);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCash() {
        return cash;
    }

    /**
     * Applies the effects of the specified sale on this trader.
     * @param stock to be sold.
     * @param quantity of stock units to be sold.
     * @param price of the stock sale.
     * @throws TradeException if trader does not have enough/any stock to fulfill the sale.
     */
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

    /**
     * Applies the effects of the specified purchase on this trader.
     * @param stock to be purchased.
     * @param quantity of stock units to be purchased.
     * @param price of the stock purchase.
     * @throws TradeException if trader does not have enough cash to fulfill the purchase.
     */
    @Override
    public void buyStock(Stock stock, Integer quantity, Double price) throws TradeException {
        if (cash - (quantity * price) >= 0) {
            cash -= quantity * price;
            if (inventory.containsKey(stock)) {
                inventory.replace(stock, inventory.get(stock) + quantity);
            } else inventory.put(stock, quantity);
        } else throw new TradeException("Buyer not enough cash.", this);
    }

    /**
     * @param stock: The quantity of this stock will be returned.
     * @return quantity of specified stock.
     */
    @Override
    public Integer getInventoryHolding(Stock stock) {
        if (inventory.containsKey(stock)) return inventory.get(stock);
        return 0;
    }

    /**
     * Does nothing.
     * @see uk.ac.glasgow.jagora.impl.RandomTrader for a simulation of trading.
     */
    @Override
    public void speak(StockExchange stockExchange) {
    }

    /**
     * @return a set of this trader's trading stocks.
     */
    @Override
    public Set<Stock> getTradingStocks() {
        Set<Stock> stockSet = inventory.keySet();
        return stockSet;
    }

}
