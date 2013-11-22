package com.lifeix.d20130911;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LJApp implements InvocationHandler {

	private Object target = null;
	
	LJApp(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Employee employee = (Employee)args[0];
		if(employee.getIncome() > 4500){
			employee.setIncome(employee.getIncome() * 0.7);
		}
		Object result = method.invoke(this.target, args);
		return result;
	}
	
	public static void main(String[] args){
		Employee[] employee = {new Employee("张三",4000),new Employee("张四",4500),new Employee("张程", 5000)};
		PaySalaryImpl ps = new PaySalaryImpl();
		LJApp app = new LJApp(ps);
		PaySalary p = (PaySalary) Proxy.newProxyInstance(ps.getClass().getClassLoader(),ps.getClass().getInterfaces(),app);
		for(Employee e:employee){
			p.paySalary(e);
		}
	}
	
}
