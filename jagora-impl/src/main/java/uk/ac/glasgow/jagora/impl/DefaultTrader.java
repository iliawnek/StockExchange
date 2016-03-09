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
     * @param name
     * @param cash
     * @param stock
     * @param quantity
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
    public void buyStock(Stock stock, Integer quantity, Double price) throws TradeException {
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
        // TODO Auto-generated method stub
    }

    @Override
    public Set<Stock> getTradingStocks() {
        Set<Stock> stockSet = inventory.keySet();
        return stockSet;
    }

}
