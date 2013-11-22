package com.lifeix.d20130828;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSHCsvReader implements CsvParser {

	private static final String SPECIAL_CHAR_A = "[^\",\\n 　]";
	private static final String SPECIAL_CHAR_B = "[^\",\\n]";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSHCsvReader CsvReader = new JSHCsvReader();
		String fileName = "I:/11/jsh.csv";
		String result = null;
		try {
			String lineText = CsvReader.readerCsv(new File(fileName));
			result = Arrays.asList(CsvReader.parse(lineText)).toString();
			System.out.println(result);
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 读取一行
	 * 
	 * @param file
	 * @return
	 */
	private String readerCsv(File file) {
		BufferedReader br = null;
		String readFile = null;
		try {
			br = new BufferedReader(new FileReader(file));
			readFile = br.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readFile;
	}

	@Override
	public String[] parse(String line) throws CsvException {
		String[] callBack = null;
		String before = null;
		String after = null;
		if (line.startsWith("\"")) {
			before = line.substring(1, line.indexOf("\","));
			after = line.substring(line.indexOf("\",") + 2);
		} else {
			if(line.indexOf(",") <= 0) {
				return new String[]{line};
			}
			before = line.substring(0, line.indexOf(","));
			after = line.substring(line.indexOf(",") + 1);
		}
		if (!verify(before)) {
			throw new CsvException();
		}
		String[] back = null;
		if (after != null || "".equals(after)) {
			back = parse(after);
			int len = back.length;
			callBack = new String[len + 1];
			callBack = contact(before,back);
		} else {
			callBack = new String[1];
			callBack[0] = before;
		}
		return callBack;
	}

	private boolean verify(String str) {
		boolean flag = true;
		for(int i=0 ; i<str.length() ; i++) {
			if(str.charAt(i) == '"') {
				if(str.charAt(++i) != '"') {
					flag = false;
				}
			}
		}
		return flag;
	}

	public String[] contact(String a, String b[]) {
		String[] f = new String[b.length + 1];
		f[0] = a;
		for (int i = 1; i < f.length; i++)
			f[i] = b[i-1];
		return f;
	}
	
	/**
	 * 注释的这部分用正则可以直接解析出来
	 */
	// @Override
	// public String[] parse(String line) throws CsvException {
	// // TODO Auto-generated method stub
	// String[] back = null;
	// String regExp = getRegExp();
	// Pattern p = Pattern.compile(regExp);
	// Matcher m = p.matcher(line);
	// int n = m.groupCount();
	// back = new String[n];
	// int i = 0;
	// while(m.find()) {
	// String str = m.group();
	// if(str.endsWith(",")) {
	// str = str.substring(0, str.length()-1);
	// }
	// if(str.startsWith("\"") && str.endsWith("\"")) {
	// str = str.substring(1, str.length()-1);
	// }
	// str = str.replaceAll("\"\"", "\"");
	//
	// System.out.println(str);
	// back[i++] = str;
	// }
	// return back;
	// }
	// /**
	// * 匹配Csv中最小单元数据的正则表达式
	// * @return
	// */
	// private String getRegExp() {
	// StringBuffer strRegExps = new StringBuffer();
	// strRegExps.append("\"((");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*[,\\n 　])*(");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*\"{2})*)*");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*\"[ 　]*,[ 　]*");
	// strRegExps.append("|");
	// strRegExps.append(SPECIAL_CHAR_B);
	// strRegExps.append("*[ 　]*,[ 　]*");
	// strRegExps.append("|\"((");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*[,\\n 　])*(");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*\"{2})*)*");
	// strRegExps.append(SPECIAL_CHAR_A);
	// strRegExps.append("*\"[ 　]*");
	// strRegExps.append("|");
	// strRegExps.append(SPECIAL_CHAR_B);
	// strRegExps.append("*[ 　]*");
	// return strRegExps.toString();
	// }
}
