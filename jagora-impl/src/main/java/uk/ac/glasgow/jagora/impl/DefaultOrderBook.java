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
	 * @param world which controls this order book's timing events.
	 */
	public DefaultOrderBook(World world) {
		this.backing = new PriorityQueue<>(new OrderBookComparator());
		this.world = world;
	}

	/**
	 * Adds an order to the order book.
	 * @param order to be recorded.
     */
	@Override
	public void recordOrder(O order) {
		backing.add(world.createTickEvent(order));
	}

	/**
	 * Removes an order from the order book.
	 * @param order to be cancelled.
     */
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

	/**
	 * Best order is determined by the compareTo() method of the orders.
	 * If order price comparison fails, ties are broken by time placed.
	 * @return the best order in the order book.
     */
	@Override
	public O getBestOrder() {
		TickEvent<O> tickEvent = backing.peek();
		if (tickEvent == null) {
			return null;
		}
		else {
			return tickEvent.getEvent();
		}
	}

	/**
	 * @return a sorted list of the orders in the order book.
     */
	@Override
	public List<TickEvent<O>> getOrdersAsList() {
		return new ArrayList<>(backing);
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
