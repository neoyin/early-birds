package com.lifeix.d20130911;


public class AWJ_PaySalaryProxy implements PaySalary {

	private PaySalary paySalary;

	public AWJ_PaySalaryProxy(PaySalary p){
		super();
		this.paySalary = p;
	}

	public static void main(String[] args) {
		Employee e1 = new Employee("张三", 4000);
		Employee e2 = new Employee("李四", 5000);
		PaySalaryImpl impl = new PaySalaryImpl();
		PaySalary pay = new AWJ_PaySalaryProxy(impl);
		pay.paySalary(e1);
		pay.paySalary(e2);
	}

	@Override
	public void paySalary(Employee employee) {
		if(employee.getIncome() > 4500){
			employee.setIncome(employee.getIncome()*0.7);
		}
		paySalary.paySalary(employee);
	}

}
