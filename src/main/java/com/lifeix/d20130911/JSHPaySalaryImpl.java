package com.lifeix.d20130911;

public class JSHPaySalaryImpl implements PaySalary {

	private PaySalary proxied;
	
	public JSHPaySalaryImpl(PaySalary proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public void paySalary(Employee employee) {
		// TODO Auto-generated method stub
		if(employee.getSalary() > 4500) {
			employee.setIncome(employee.getSalary() * 0.7);
		}
		proxied.paySalary(employee);
	}
	public static void main(String[] args) {
		Employee e1 = new Employee("张三", 4000);
		Employee e2 = new Employee("张三", 5000);
		PaySalary payImpl = new PaySalaryImpl();
		PaySalary pay = new JSHPaySalaryImpl(payImpl);
		pay.paySalary(e1);
		pay.paySalary(e2);
	}
}
