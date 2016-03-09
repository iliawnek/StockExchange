package uk.ac.glasgow.jagora.impl;

import java.util.*;

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
		this.world = world;
	}
	
	@Override
	public void recordOrder(O order) {
		backing.add(world.createTickEvent(order));
	}

	@Override
	public void cancelOrder(O order) {
		for (TickEvent<O> tickEvent : backing) {
			Order o = tickEvent.getEvent();
			if (o.equals(order)) {
				backing.remove(tickEvent);
				return;
			}
		}
	}

	@Override
	public O getBestOrder() {
		return backing.peek().getEvent();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TickEvent<O>> getOrdersAsList() {
		List list = new ArrayList<>();
		for (TickEvent<O> t : backing) {
			list.add(t);
		}
		return list;
	}
	
	private class OrderBookComparator implements Comparator<TickEvent<O>> {

		@Override
		public int compare(TickEvent<O> tickEvent1, TickEvent<O> tickEvent2) {
			int score = tickEvent1.getEvent().compareTo(tickEvent2.getEvent());
			if (score == 0) {
				return tickEvent1.getTick().compareTo(tickEvent2.getTick());
			}
			else {
				return score;
			}
		}
	}
	
	@Override
	public String toString (){
		return backing.toString();
	}

}
