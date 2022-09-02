package com.majong.zelda.api.tickutils;

public abstract class Ticker<T> implements ITickable{
	private final T[] paraments;
	public Ticker(T... paraments) {
		this.paraments=paraments;
		TickManager.registerTicker(this);
		this.init();
	}
	public void init() {}
	public T[] getParaments() {
		return paraments;
	}
	public void remove() {
		TickManager.removeTicker(this);
	}
}
