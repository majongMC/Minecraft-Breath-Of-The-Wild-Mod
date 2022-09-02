package com.majong.zelda.api.tickutils;

public abstract class Processable<T> extends Ticker<T>{
	private int process=0;
	public Processable(T... paraments) {
		super(paraments);
	}
	@Override
	public void tick() {
		process++;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process=process;
	}
}
