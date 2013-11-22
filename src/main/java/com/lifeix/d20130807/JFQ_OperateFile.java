
package com.lifeix.d20130807;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JFQ_OperateFile {

	private List<Employee> readFile(String path) throws IOException {
		File file = new File(path);
		List<Employee> list = new ArrayList<Employee>();
		if(file.exists()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "";
			while((line = br.readLine()) != null) {
				Employee em = new Employee();
				String[] datas = line.split("\\|");
				em.setName(datas[0]);
				em.setLongno(Long.parseLong(datas[1].trim()));
				em.setSalary(Double.parseDouble(datas[2].trim()));
				list.add(em);
			}
			br.close();
		}
		return list;
	}
	
	private Double calculateInCome(Employee e, File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		CalculateImpl ci = (CalculateImpl) ois.readObject();
		ois.close();
		return ci.calculate(e);
	}
	
	private void writeFile(List<Employee> list, String name) throws IOException {
		File file = new File(this.getClass().getResource("").getFile() + name);
		if(!file.exists()) {
			file.createNewFile();
		}
		System.out.println(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		for(Employee e : list) {
			bw.write(e.getName() + "|" + e.getLongno() + "|" + e.getSalary() + "|" + e.getIncome() + "\n");
		}
		bw.close();
	}
	
	public static void main(String[] args) {
		JFQ_OperateFile jfq = new JFQ_OperateFile();
		try {
			List<Employee> list = jfq.readFile(JFQ_OperateFile.class.getResource("salary.txt").getFile());
			File file = new File(JFQ_OperateFile.class.getResource("impl.ser").getFile());
			for(Employee e : list) {
				e.setIncome(jfq.calculateInCome(e, file).doubleValue());
				if(e.getIncome() > 1000.0) {
					System.out.println(e.getName() + "的税后工资为" + e.getIncome());
				}
			}
			String name = "JFQ_salary.txt";
			jfq.writeFile(list, name);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

