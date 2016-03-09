package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.Stock;

public class DefaultStock implements Stock{
	
	private String name;
	
	public DefaultStock(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
