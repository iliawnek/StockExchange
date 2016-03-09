package uk.ac.glasgow.jagora.impl;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import uk.ac.glasgow.jagora.Order;
import uk.ac.glasgow.jagora.OrderBook;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.World;

/**
 * Provides the default implementation of an order book for sorting buy and sell orders.
 * @author tws
 */
public class DefaultOrderBook<O extends Order & Comparable<O>> implements OrderBook<O> {

	private final Queue<TickEvent<O>> backing;
	private World world;
	
	/**
	 * Constructs a new instance of the order book synchronized to the ticks of the specified world.
	 * @param world
	 */
	public DefaultOrderBook(World world) {
		this.backing = new PriorityQueue<TickEvent<O>>(new OrderBookComparator());
		//TODO
	}
	
	@Override
	public void recordOrder(O order) {
		//TODO
	}

	@Override
	public void cancelOrder(O order) {
		//TODO
	}

	@Override
	public O getBestOrder() {
		//TODO
		return null;
	}

	@Override
	public List<TickEvent<O>> getOrdersAsList() {
		//TODO
		return null;
	}
	
	private class OrderBookComparator implements Comparator<TickEvent<O>> {

		@Override
		public int compare(TickEvent<O> tickEvent1, TickEvent<O> tickEvent2) {
			//TODO
			return 0;
		}
	}
	
	@Override
	public String toString (){
		// TODO
		return null;
	}

}
