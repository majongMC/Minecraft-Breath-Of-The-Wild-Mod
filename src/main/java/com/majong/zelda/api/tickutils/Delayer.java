package com.majong.zelda.api.tickutils;

public abstract class Delayer<T> extends Processable<T>{
	private final int delaytime;
	public Delayer(int delaytime,T... paraments) {
		super(paraments);
		this.delaytime=delaytime;
	}
	@Override
	public void tick() {
		super.tick();
		if(this.getProcess()>delaytime) {
			this.finish();
			this.remove();
		}
	}
	public abstract void finish();
}
