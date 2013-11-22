package com.lifeix.d20130828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AWJ_Csv implements CsvParser{


	@Override
	public String[] parse(String line) throws CsvException {
		String regex = "((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)(,)";
		Matcher matcher = Pattern.compile(regex).matcher(line);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String field;
			field = matcher.group(1).replaceAll("\"","");
			sb.append(field+",");
		}
		String result = sb.toString().substring(0,sb.length()-1);
		System.out.println(result);
		return result.split(",");
	}

	/**
	 * 读取内容
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<String []> readFile(String path) throws IOException{
		FileInputStream fr = new FileInputStream(path); 
		InputStreamReader input = new InputStreamReader(fr,"UTF-8"); 
		BufferedReader reader=new BufferedReader(input); 
		String line = "";
		List<String []> arryas = new ArrayList<String[]>();
		while ((line = reader.readLine()) != null) {
			try {
				String[] csv = parse(line);
				if(csv.length>0){
					arryas.add(csv);
				}
			} catch (CsvException e) {
				System.out.println(e.getMessage()+":"+line);
			}

		}
		return arryas;
	}

	/**
	 * 写入csv文件
	 * @param path
	 * @param csvs
	 * @throws IOException
	 */
	public  void writeCsv(String path, List<String []> csvs) throws IOException{
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(path,Boolean.FALSE),"GB2312");
		BufferedWriter writer = new BufferedWriter(output);
		for(String[] csv : csvs){
			String line = Arrays.asList(csv).toString();
			int len = line.length();
			writer.write(line.substring(1,len-1));
			writer.newLine();
		}
		writer.close();
	}

	public static void main(String[] args) {
		String path = AWJ_Csv.class.getResource("awj.txt").getFile();
		String csvPath = "e:\\awj.csv";
		AWJ_Csv csvRead = new AWJ_Csv();
		try {
			List<String[]> csvsList;
			csvsList = csvRead.readFile(path);
			csvRead.writeCsv(csvPath, csvsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
