package com.lifeix.d20130911;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JSH_PaySalaryImpl implements InvocationHandler  {

	private PaySalary proxied;
	
	public JSH_PaySalaryImpl(PaySalary proxied) {
		this.proxied = proxied;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e1 = new Employee("张三", 4000);
		Employee e2 = new Employee("张三", 5000);
		PaySalary payImpl = new PaySalaryImpl();
		PaySalary pay = (PaySalary)Proxy.newProxyInstance(PaySalary.class.getClassLoader(),
				new Class[] {PaySalary.class},new JSH_PaySalaryImpl(payImpl));
		pay.paySalary(e1);
		pay.paySalary(e2);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Employee[] employes = new Employee[args.length];
		if(args != null) {
			int i = 0;
			for(Object obj : args) {
				Employee employee = (Employee)obj;
				if(employee.getSalary() > 4500) {
					employee.setIncome(employee.getSalary() * 0.7);
				}
				employes[i++] = employee;
			}
		}
		return method.invoke(proxied, employes);
	}
}
