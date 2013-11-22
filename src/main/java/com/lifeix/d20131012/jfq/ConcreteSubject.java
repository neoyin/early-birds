package com.lifeix.d20131012.jfq;

public class ConcreteSubject extends AbstractSubject {

	public ConcreteSubject(float tempor, float shi, float as) {
		super(tempor, shi, as);
	}

	@Override
	public void push() {
		this.notifyObserver();
	}

}
