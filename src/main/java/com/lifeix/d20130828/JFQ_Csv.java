package com.lifeix.d20130828;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-28 下午05:10:00
 **/
public class JFQ_Csv implements CsvParser{

	/**@description:	
	 * @return:void
	 * @param args
	 * @throws CsvException 
	 * @throws IOException 
	 */

	public static void main(String[] args) throws CsvException, IOException {
		File f = new File(JFQ_Csv.class.getResource("test.txt").getFile());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = "";
		JFQ_Csv jfq = new JFQ_Csv();
		while((line = br.readLine()) != null) {
			jfq.parse(line);
		}
		
	//	new JFQ_Csv().parse("The Art, Fantasy.jpg,ab,c,d\"efds\"fsdfsd,ghi");
	}
	public String[] parse(String line) throws CsvException {
		StringBuffer sb = new StringBuffer();
		if(null != line && !"".equals(line)) {
			String[] strs = line.split(",");
			for(String str : strs) {
				int index = str.indexOf("\"");
				int next = str.substring(index+1).indexOf("\"");
				if(index == 0 && next < 0) {
					System.out.println(str+"格式错误");
					throw new CsvException();
				} else {
					sb.append(replace(str)).append(",");
				}
				
			}
		}
		System.out.println(sb.toString());
		return sb.subSequence(0, sb.length() - 1).toString().split(",");
	}

	private String replace(String str) throws CsvException {
		int count = str.split("\"").length;
		if(str.indexOf("\"") >= 0) {
			if(count%2 !=0) {
				String temp = str.substring(str.lastIndexOf("\""));
				temp = temp.replace("\"", "\"");
				str = str.substring(0, str.lastIndexOf("\"")) + temp;
			} else if(count == 2){
				if(str.indexOf("\"") == 0 && str.lastIndexOf("\"") == (str.length() -1)) {
					return str;
				}
			}
			 if(str.lastIndexOf("\"\"") > 0) {
				str += "\"";
			} else if(str.lastIndexOf("\"") > 0 && count == 1) {
				str = str.replace("\"", "\"\"");
			} else {
				 boolean flag = false;
				 if(str.lastIndexOf("\"") > 0 && count%2==0) {
					 flag = true;
				 }
				str = str.replaceAll("\"", "\"\"");
				//str = "\"" + str + "\"";
				if(flag) {
					str = str.substring(0, str.length() - 2);
				}
			}
			 str = "\"" + str + "\"";
		} else if(str.indexOf(",") >= 0) {
			str = "\"" + str + "\"";
		}
		//System.out.println(str);
		return str;
	}
}

