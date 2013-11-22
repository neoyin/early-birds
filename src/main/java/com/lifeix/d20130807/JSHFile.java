package com.lifeix.d20130807;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class JSHFile {
	
	private static List<Employee> employee = new ArrayList<Employee>();
	
	public static void main(String[] args) {
		readFile();
		reverse();
		writeFile();
	}
	public static void readFile(){
		File file = new File("src/main/java/com/lifeix/d20130807/salary.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String readStr = null;
			while((readStr = reader.readLine()) != null) {
				Employee e = new Employee();
				String[] info = readStr.split("\\|");
				e.setName(info[0]);
				e.setLongno(Long.valueOf(info[1]));
				e.setSalary(Double.valueOf(info[2]));
				employee.add(e);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(Employee e : employee) {
			System.out.println(e.toString());
		}
		System.out.println();
	}
	public static void reverse() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/java/com/lifeix/d20130807/impl.ser"));
			CalculateImpl obj = (CalculateImpl) in.readObject();
			for(Employee e : employee) {
				e.setIncome(obj.calculate(e));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		for(Employee e : employee) {
			if(e.getIncome() > 1000) {
				System.out.println(e.getName());
			}
		}
		System.out.println();
	}
	public static void writeFile() {
		File file = new File("src/main/java/com/lifeix/d20130807/jshsalary.txt");
		if(file.exists()) {
			file.delete();
		}
		BufferedWriter write = null;
		try {
			file.createNewFile();
			write = new BufferedWriter(new FileWriter(file));
			for(Employee e : employee) {
				write.write(e.toString()+"\n");
			}
			write.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(write != null) {
				try {
					write.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
