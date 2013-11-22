package com.lifeix.d20131012.jfq;

public class TestObserver {
	public static void main(String []args) {
		AbstractSubject as = new ConcreteSubject(5.0f,20f,3f);
		IBlankObserver ib = new BlankObjserver();
		as.attachment(ib);
		as.attachment(new BlankObjserver2());
		as.push();
	}
}
