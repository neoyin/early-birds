package com.lifeix.d20130807;

import java.io.Serializable;

public class CalculateImpl implements CalculateSalary, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5587515211468696138L;

	public double calculate(Employee e) {
		if(e.getSalary() == 500000){
			return 1000.1;
		}else{
			return 1000;
		}
	}

}
