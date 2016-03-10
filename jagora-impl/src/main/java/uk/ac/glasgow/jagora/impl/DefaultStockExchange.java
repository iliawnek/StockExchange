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

    private Map<Stock, Market> markets;
    private World world;
    private List<TickEvent<Trade>> tradeHistory;

    public DefaultStockExchange(World world) {
        this.world = world;
        markets = new HashMap<>();
        tradeHistory = new ArrayList<>();
    }

    @Override
    public void doClearing() {
        for (Map.Entry<Stock, Market> entry : markets.entrySet()) {
            tradeHistory.addAll(entry.getValue().doClearing());
        }
    }

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

    @Override
    public void cancelBuyOrder(BuyOrder buyOrder) {
        Market market = markets.get(buyOrder.getStock());
        if (market == null) return;
        market.cancelBuyOrder(buyOrder);
    }

    @Override
    public void cancelSellOrder(SellOrder sellOrder) {
        Market market = markets.get(sellOrder.getStock());
        if (market == null) return;
        market.cancelSellOrder(sellOrder);
    }

    @Override
    public Double getBestOffer(Stock stock) {
        Market market = markets.get(stock);
        if (market == null) return null;
        return market.getBestOffer();
    }

    @Override
    public Double getBestBid(Stock stock) {
        Market market = markets.get(stock);
        if (market == null) return null;
        return market.getBestBid();
    }

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
