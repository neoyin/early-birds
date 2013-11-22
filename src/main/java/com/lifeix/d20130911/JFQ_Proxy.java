package com.lifeix.d20130911;
/**
 * @DESCRIPTION:	
 * @DATE:2013-9-11 下午06:00:34
 **/
public class JFQ_Proxy implements PaySalary{

	public void paySalary(Employee employee) {
		if(employee.getIncome() > 4500) {
			employee.setIncome(employee.getIncome()*0.7);
		} 
		new PaySalaryImpl().paySalary(employee);
	}
	public static void main(String[] args) {
		Employee e1 = new Employee("张三", 4000);
		Employee e2 = new Employee("张三", 5000);
		PaySalary pay = new JFQ_Proxy();
		pay.paySalary(e1);
		pay.paySalary(e2);
	}
}

