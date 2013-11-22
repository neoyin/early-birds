package com.lifeix.d20130807;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AWJ_20130807 {

	public static void main(String[] args) {
		System.out.println("1.salary.txt 记录员工的 姓名|龙号|工资, 写一个读取文件的方法, 将每一行解析为一个Employee对象");
		String filePath = AWJ_20130807.class.getResource("salary.txt").getFile();
		List<Employee> employees = readTxtFile(filePath);
		System.out.println("\n\n");

		System.out.println("2.接口CalculateSalary的calculate方法可以根据员工的工资计算税后工资, 文件impl.ser中保存了一个实现了CalculateSalary接口的对象的序列化内容,"
				+"将impl.ser中的对象反序列化, 然后利用该对象计算税后工资, 打印出税后工资大于1000的员工.");
		String path = AWJ_20130807.class.getResource("impl.ser").getFile();
		CalculateImpl calculateImpl = null;
		try {
			calculateImpl = calculateImpl = readObject(new File(path));
			for(Employee employee : employees){
				employee.setIncome(calculateImpl.calculate(employee));
				if(calculateImpl.calculate(employee) >1000){
					System.out.println(employee.getName()+"税后工资大于1000");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n");

		System.out.println("3.写文件, 将所有员工的信息(包含税后工资写入新的文件)");
		String newPath = AWJ_20130807.class.getResource("").toString();
		newPath = newPath.substring(6,newPath.length());
		writerTextFile(newPath,employees);
	}


	/**
	 * 读取文件
	 * @param filePath
	 */
	public static List<Employee> readTxtFile(String filePath){
		List<Employee> employees = new ArrayList<Employee>();
		try {
			String encoding="utf-8";
			File file=new File(filePath);
			Map<String, Integer> ids = new HashMap<String, Integer>();
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					Employee  employee = new Employee();
					String str [] = lineTxt.split("[|]");
					employee.setName(str[0]);
					employee.setLongno(Long.valueOf(str[1]));
					employee.setSalary(Double.valueOf(str[2]));
					employees.add(employee);
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件");
			}
			for(Employee employee : employees){
				System.out.println(employee.getName()+"的龙号为："+employee.getLongno()+",工资为："+employee.getSalary());
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return employees;
	}

	/**
	 * 反序列化
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static CalculateImpl readObject(File f) throws Exception{
		InputStream is=new FileInputStream(f);
		ObjectInputStream ois=new ObjectInputStream(is);
		return (CalculateImpl)ois.readObject();
	}

	/**
	 * 写入文件
	 * @param path
	 * @param content
	 */
	public static void writerTextFile(String path, List<Employee> employees) {
		File dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "new.txt", false));
			for(Employee employee : employees){

				bw.write("姓名："+employee.getName()+" 龙号："+employee.getLongno()+" 税前工资："+employee.getSalary()+" 税后工资："+employee.getIncome()+"\r\n");

			}
			System.out.println("写入完毕，目录："+path+"new.txt");
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
