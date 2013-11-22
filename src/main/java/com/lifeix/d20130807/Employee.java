package com.lifeix.d20130807;

public class Employee {
	/** 姓名 */
	private String name;
	/** 龙号 */
	private long longno;
	/** 工资 */
	private double salary;
	/** 税后工资 */
	private double income;
	
	public String toString(){
		return name + "|" + longno + "|" + salary + "|" + income;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLongno() {
		return longno;
	}
	public void setLongno(long longno) {
		this.longno = longno;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
}
