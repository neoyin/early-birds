package com.lifeix.d20130911;

public class ZDL_PaySalaryImpl implements PaySalary{

	
	
	@Override
	public void paySalary(Employee employee) {
		if(employee.getSalary()>4500){
			employee.setIncome(employee.getIncome()*0.75);
		}
		PaySalary paysalary = new PaySalaryImpl();
		paysalary.paySalary(employee);
	}
	public static void main(String[] args) {
		Employee e1 = new Employee("张三", 4000);
		Employee e2 = new Employee("张三", 5000);
		PaySalary pay = new ZDL_PaySalaryImpl();
		pay.paySalary(e1);
		pay.paySalary(e2);
	}
}
