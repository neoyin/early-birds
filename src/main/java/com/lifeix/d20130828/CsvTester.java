package com.lifeix.d20130828;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvTester {
	public static void main(String[] args) {
		CsvParser parser = new CsvParser() {
			@Override
			public String[] parse(String line) throws CsvException {
				if(line.indexOf("\"") > 0){
					throw new CsvException();
				}else{
					return line.split(",");
				}
			}
		};
		
		testParser(parser);
		testParser(new AWJ_Csv());
		testParser(new JFQ_Csv());
		testParser(JLApp.getParser());
		testParser(new JSHCsvReader());
//		testParser(new ZDL_CSV());

	}

	private static void testParser(CsvParser parser) {
		List<String> lines = new ArrayList<String>();
		lines.add("abc,def,ghi");
		lines.add("abc,\"def\",ghi");
		lines.add("abc,\"d,ef\",ghi");
		lines.add("abc,\"d,e\"\"f\",ghi");
		lines.add("abc,\"d,e\"f\",ghi");
		lines.add("abc,\"\"d,ef\",ghi");
		
		List<String> values = new ArrayList<String>();
		values.add("[abc, def, ghi]");
		values.add("[abc, def, ghi]");
		values.add("[abc, d,ef, ghi]");
		values.add("[abc, de\"f, ghi]");
		values.add("wrong format!");
		values.add("wrong format!");
		
		System.out.println("parser :" + parser.getClass().getName() + "===============================================");
		for(int i=0;i<lines.size();i++){
			String line = lines.get(i);
			String result = null;
			try {
				result = Arrays.asList(parser.parse(line)).toString();
			} catch (CsvException e) {
				result = "wrong format!";
			}
			boolean test = result.equals(values.get(i));
			System.out.println(test + "--" + line + "========" + result);
		}
	}
}
