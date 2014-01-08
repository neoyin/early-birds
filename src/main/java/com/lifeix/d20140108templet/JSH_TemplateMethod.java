public class JSH_TemplateMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractTemplate coffee = new Coffee();
		coffee.templateMethod();
		System.out.println();
		AbstractTemplate tea = new Tea();
		tea.templateMethod();
	}

}

abstract class AbstractTemplate {
	
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
class Coffee extends AbstractTemplate {

	@Override
	protected void config() {
		System.out.println("Mixed coffee and water");
	}
}
class Tea extends AbstractTemplate {

	@Override
	protected void config() {
		System.out.println("Mixed tea and water");
	}
	@Override
	protected void flush() {
		System.out.println("Pour a pot");
	}
}