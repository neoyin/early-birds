package com.lifeix.d20130807;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LjApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		List<Employee> users = read();
		CalculateSalary salary = Sequence();
		write(salary, users);
	}
	/**
	 * 计算税后工资，并写
	 * @param salary
	 * @param users
	 */
	private static void write(CalculateSalary salary, List<Employee> users){
		if(salary != null){
			try {
				StringBuilder newTxt = new StringBuilder();
				for(Employee user : users){
					double income = salary.calculate(user);
					user.setIncome(income);
					String newOne = user.toString();
					if(income > 1000.0){	
						System.out.println(newOne);
					}
					newTxt.append(newOne);
					newTxt.append("\r\n");
				}
				FileWriter fw = new FileWriter("src/main/java/com/lifeix/d20130807/jiangLi.txt",false);
				BufferedWriter write = new BufferedWriter(fw);
				write.write(newTxt.toString());
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 反序列
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static CalculateSalary Sequence() throws ClassNotFoundException{
		try {
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("src/main/java/com/lifeix/d20130807/impl.ser"));
			CalculateSalary salary = (CalculateSalary)in.readObject();
			in.close();
			return salary;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 读取成员信息
	 * @return
	 * @throws IOException
	 */
	private static List<Employee> read() throws IOException{
		List<Employee> users = new ArrayList<Employee>();
		try {
			BufferedReader read = new BufferedReader(new FileReader("src/main/java/com/lifeix/d20130807/salary.txt"));
			String line = null;
			while((line = read.readLine()) != null){
				Employee ployee = new Employee();
				String[] info = line.split("\\|");
				ployee.setName(info[0]);
				ployee.setLongno(Long.parseLong(info[1]));
				ployee.setSalary(Double.parseDouble(info[2]));
				users.add(ployee);
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return users;
	}
}
