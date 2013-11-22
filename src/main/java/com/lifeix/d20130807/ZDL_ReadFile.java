package com.lifeix.d20130807;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;


public class ZDL_ReadFile {
	public static void main(String[] args) {
		InputStream implInputStream=null;
		InputStream salaryInputStream=null;
		PrintWriter pw=null;
		String PATH = ZDL_ReadFile.class.getResource("/com/lifeix/d20130807/").getPath();
		PATH = PATH.substring(1);
		try {
			implInputStream=new FileInputStream(PATH+new File("impl.ser"));
			salaryInputStream=new FileInputStream(PATH+new File("salary.txt"));
			pw = new PrintWriter(new File(PATH+"zdl_salary.txt"));
			ObjectInputStream ois = new ObjectInputStream(implInputStream);
			CalculateSalary ob = (CalculateSalary) ois.readObject();
			BufferedReader br =new BufferedReader(new InputStreamReader(salaryInputStream,"utf-8"));
			String salaryLine = null;
			while((salaryLine = br.readLine())!=null){
				String salaryArray[] = salaryLine.split("[|]");
				Employee employee=new Employee();
				employee.setName(salaryArray[0]);
				employee.setLongno(Long.valueOf(salaryArray[1]));
				employee.setSalary(Double.valueOf(salaryArray[2]));
				double icome =ob.calculate(employee);
				employee.setIncome(icome);
				if(employee.getIncome()>1000){
					System.out.println(employee.getName());
				}
				pw.println(employee);
				pw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				implInputStream.close();
				salaryInputStream.close();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}


	}
}
