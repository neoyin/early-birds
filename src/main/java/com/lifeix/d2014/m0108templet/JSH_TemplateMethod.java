package com.lifeix.d2014.m0108templet;

public class JSH_TemplateMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractTemplate2 coffee = new Coffee2();
		coffee.templateMethod();
		System.out.println();
		AbstractTemplate2 tea = new Tea();
		tea.templateMethod();
	}

}

abstract class AbstractTemplate2 {
	
	public void templateMethod() {
		waterBoil();
		config();
		flush();
	}
	/**
	 * Boil water
	 */
	protected void waterBoil() {
		System.out.println("water is boiling");
	}
	/**
	 * Mixed material and water
	 */
	protected abstract void config();
	/**
	 * Pour a cup
	 */
	protected void flush() {
		System.out.println("Pour a cup");
	}
}
class Coffee2 extends AbstractTemplate2 {

	@Override
	protected void config() {
		System.out.println("Mixed coffee and water");
	}
}
class Tea extends AbstractTemplate2 {

	@Override
	protected void config() {
		System.out.println("Mixed tea and water");
	}
	@Override
	protected void flush() {
		System.out.println("Pour a pot");
	}
}