package uk.ac.glasgow.jagora.impl;

import java.util.*;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;

public class DefaultStockExchange implements StockExchange {

    /**
     * Map of markets in the stock exchange.
     */
    private Map<Stock, Market> markets;
    private World world;
    /**
     * List of trades made in the stock exchange, ordered from old to new.
     */
    private List<TickEvent<Trade>> tradeHistory;

    /**
     * Constructs a new stock exchange synchronised to the ticks of the specified world.
     * @param world which controls the stock exchange's timing events.
     */
    public DefaultStockExchange(World world) {
        this.world = world;
        markets = new HashMap<>();
        tradeHistory = new ArrayList<>();
    }

    /**
     * Performs clearing for each market in the stock exchange.
     * @see uk.ac.glasgow.jagora.impl.ContinuousOrderDrivenMarket#doClearing()
     */
    @Override
    public void doClearing() {
        for (Map.Entry<Stock, Market> entry : markets.entrySet()) {
            tradeHistory.addAll(entry.getValue().doClearing());
        }
    }

    /**
     * Adds the buy order to the corresponding market in the stock exchange.
     * Creates the market if it does not yet exist in the stock exchange.
     * @param buyOrder to be placed.
     */
    @Override
    public void placeBuyOrder(BuyOrder buyOrder) {
        Stock stock = buyOrder.getStock();
        Market market = markets.get(stock);
        if (market == null) {
            market = new ContinuousOrderDrivenMarket(stock, world);
            markets.put(stock, market);
        }
        market.placeBuyOrder(buyOrder);
    }

    /**
     * Adds the sell order to the corresponding market in the stock exchange.
     * Creates the market if it does not yet exist in the stock exchange.
     * @param sellOrder to be placed.
     */
    @Override
    public void placeSellOrder(SellOrder sellOrder) {
        Stock stock = sellOrder.getStock();
        Market market = markets.get(stock);
        if (market == null) {
            market = new ContinuousOrderDrivenMarket(stock, world);
            markets.put(stock, market);
        }
        market.placeSellOrder(sellOrder);
    }

    /**
     * Removes the buy order from the corresponding market in the stock exchange.
     * @param buyOrder to be cancelled.
     */
    @Override
    public void cancelBuyOrder(BuyOrder buyOrder) {
        Market market = markets.get(buyOrder.getStock());
        if (market == null) return;
        market.cancelBuyOrder(buyOrder);
    }

    /**
     * Removes the sell order from the corresponding market in the stock exchange.
     * @param sellOrder to be cancelled.
     */
    @Override
    public void cancelSellOrder(SellOrder sellOrder) {
        Market market = markets.get(sellOrder.getStock());
        if (market == null) return;
        market.cancelSellOrder(sellOrder);
    }

    /**
     * @param stock of the best offer.
     * @return the best offer of the specified stock.
     */
    @Override
    public Double getBestOffer(Stock stock) {
        Market market = markets.get(stock);
        if (market == null) return null;
        return market.getBestOffer();
    }

    /**
     * @param stock of the best bid.
     * @return the best bid of the specified stock.
     */
    @Override
    public Double getBestBid(Stock stock) {
        Market market = markets.get(stock);
        if (market == null) return null;
        return market.getBestBid();
    }

    /**
     * @param stock of the trades in the returned trade history list.
     * @return a list of trades performed involving the specified stock; ordered from old to new.
     */
    @Override
    public List<TickEvent<Trade>> getTradeHistory(Stock stock) {
        List<TickEvent<Trade>> stockTradeHistory = new ArrayList<>();
        for (TickEvent<Trade> te : tradeHistory) {
            if (te.getEvent().getStock() == stock) {
                stockTradeHistory.add(te);
            }
        }
        return stockTradeHistory;
    }

}
