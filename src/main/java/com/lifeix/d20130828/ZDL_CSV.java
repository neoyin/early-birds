package com.lifeix.d20130828;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZDL_CSV {

	public static void main(String[] args) {
		ZDL_CSV csv= new ZDL_CSV();
		try {
			csv.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFile() throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bur = new BufferedReader(new InputStreamReader(new FileInputStream("d://abc.csv")));//读取文件
		String line =null;
		StringBuilder stb= new StringBuilder();
		ZDL_CSV.CsvParser csvParser =new ZDL_CSV.CsvParserImpl();
		while((line=bur.readLine())!=null){
			line = csvParser.parse(line);//匹配每一行文本
			stb .append(line);
		}
		System.out.println(stb);

	}
	public interface CsvParser{
		public String parse(String line);
	}
	class CsvParserImpl implements ZDL_CSV.CsvParser{
		@Override
		public String parse(String line) {
			String dcells [] = line.split(",");//每个单元格内容
			if(dcells.length>0){
				String []mcells = dcells[0].split(":");
				boolean onecell = mcells.length==2&&mcells[0].substring(0,1).equals("(");//判断第一个单元格格式是否为异常格式
				if(onecell){
					System.out.println(dcells.length);
					boolean twocell = dcells[1].substring(dcells.length).equals(")");//判断第二个单元格格式是否为异常格式
					if(twocell){//是否为(ex: aa格式
						new ZDL_CSV.CsvException("格式错误");
						return "";
					}
				}
			}
			return line;
		}
	}
	class CsvException extends Exception{
		CsvException(String exception){
			super(exception);
			System.out.println(exception);
		}
	}
}
