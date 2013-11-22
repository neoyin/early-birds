package com.lifeix.d20130807;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ResultTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream input = new FileInputStream(new File("/home/lifeix/workspace_indigo/lifeix-growth-road/src/main/java/com/lifeix/d20130807/impl.ser"));
		ObjectInputStream ois = new ObjectInputStream(input);
		
		CalculateSalary cal  =  (CalculateSalary)ois.readObject();
		Employee e = new Employee();
		e.setSalary(500000);
		
		System.out.println(cal.calculate(e));
	}
}
