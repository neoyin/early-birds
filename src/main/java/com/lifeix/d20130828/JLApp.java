package com.lifeix.d20130828;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JLApp{
	public static void main(String[] args) throws IOException{
        File cvsFile = new File("src/main/java//com/lifeix/d20130828/JLtest.csv");   
        FileReader fileReader = null;   
        BufferedReader bufferedReader = null;   
        fileReader = new FileReader(cvsFile);   
        bufferedReader = new BufferedReader(fileReader);   
  
        String strLine = "";   
        String sp = "|";
        String n = "\n";
        StringBuilder str = new StringBuilder();
        int j = 0;
        while ((strLine = bufferedReader.readLine()) != null) {  
        	try{
//        		if(strLine.startsWith(" "))throw new CsvException();	//不能空开头
            	CsvParser parser = Parser.getParser();
            	String s[] = parser.parse(strLine);
            	for(int i=0;i<s.length;i++){
            		str.append(s[i]).append(sp);
            	}
            	str.append(n);
            	j++;
        	}catch(CsvException e){
        		j++;
        		System.out.println("error:"+j+":"+strLine);
        		e.printStackTrace();
        	}
        }   
        fileReader.close();
        bufferedReader.close();
        System.out.println(str.toString());
	}
	
	public static CsvParser getParser(){
		return Parser.getParser();
	}

}

class Parser {
	public static CsvParser getParser(){
		CsvParser parser = new CsvParser() {
			@Override
			public String[] parse(String line) throws CsvException {
				int i = 0;
				StringBuilder str = new StringBuilder();
				List<String> res = new ArrayList<String>();
				while(i<line.length()){		//开始遍历字符
					String s = line.substring(i, i+1);
					if(s.equals(",")){
						if(str.length()>=1){res.add(str.toString());str = new StringBuilder();} //以逗号分隔为一个字段
						else{res.add(" ");}	//空字段
						i++;
					}else{
						if(s.equals("\"") ){	//\"开头必须以"结束
							if(str.length()>0)throw new CsvException();
							else{
								i++;
								while(i<line.length()){
									String se = line.substring(i, i+1);
									if(se.equals("\"")){	//结尾"或者中间两个"
										i++;
										if(i>=line.length()){res.add(str.toString());str = new StringBuilder();break;}//结尾不用","标示
										else{
											String s2 = line.substring(i,i+1);
											if(s2.equals(",")){res.add(str.toString());str = new StringBuilder();break;}
											if(!s2.equals("\""))throw new CsvException();	//第二个"后面不跟,必须是双"
											str.append(se).append(s2);
											i++;
										}
									}else{str.append(se);i++;}
								}
								if(str.length() > 0)throw new CsvException();	//"开头最终也没找到结尾，为错误
							}
						}else{str.append(s);}
						i++;
					}
				}
				if(str.length() > 0)res.add(str.toString());
				String[] csv = new String[res.size()];
		        for(int k=0;k<res.size();k++){
		        	csv[k]=res.get(k);
		        }
		        return csv;
			}
		};
		return parser;
	}
}
//class Parser implements CsvParser{
//	private static final String SPECIAL_CHAR_A = "[^\",\\n 　]";  
//    private static final String SPECIAL_CHAR_B = "[^\",\\n]"; 
//	@Override
//	public String[] parse(String line) throws CsvException{
////		int k = 0;
////		while(k<line.length()){
////			
////		}
//        String regExp = getRegExp();  
//        String str = "";  
//        Pattern pattern = Pattern.compile(regExp);  
//        Matcher matcher = pattern.matcher(line);  
//        List<String> list = new ArrayList<String>();
//        while(matcher.find()) {  
//            str = matcher.group();  
//            str = str.trim();  
//            if (str.endsWith(",")){  
//                str = str.substring(0, str.length()-1);  
//                str = str.trim();  
//            }  
//            if (str.startsWith("\"") && str.endsWith("\"")) {  
//                str = str.substring(1, str.length()-1);  
//                if (isExisted("\"\"", str)) {  
//                    str = str.replaceAll("\"\"", "\"");  
//                }  
//            }  
//            if (!"".equals(str)) {  
//                list.add(str);
//            }  
//        }  
//        String[] csv = new String[list.size()];
//        for(int i=0;i<list.size();i++){
//        	csv[i]=list.get(i);
//        }
//        return csv;
//	        
//	}
//	/** 
//     * 正则表达式。 
//     * @return 匹配csv文件里最小单位的正则表达式。 
//     */  
//    private String getRegExp() {  
//          
//        String strRegExp = "";  
//          
//        strRegExp =  
//            "\"(("+ SPECIAL_CHAR_A + "*[,\\n 　])*("+ SPECIAL_CHAR_A + "*\"{2})*)*"+ SPECIAL_CHAR_A + "*\"[ 　]*,[ 　]*"  
//            +"|"+ SPECIAL_CHAR_B + "*[ 　]*,[ 　]*"  
//            + "|\"(("+ SPECIAL_CHAR_A + "*[,\\n 　])*("+ SPECIAL_CHAR_A + "*\"{2})*)*"+ SPECIAL_CHAR_A + "*\"[ 　]*"  
//            + "|"+ SPECIAL_CHAR_B + "*[ 　]*";  
//          
//        return strRegExp;  
//    }  
//    /** 
//     * @param argChar 
//     * @param argStr 
//     * @return 
//     */  
//    private boolean isExisted(String argChar, String argStr) {  
//          
//        boolean blnReturnValue = false;  
//        if ((argStr.indexOf(argChar) >= 0)  
//                && (argStr.indexOf(argChar) <= argStr.length())) {  
//            blnReturnValue = true;  
//        }  
//        return blnReturnValue;  
//    }  
//	
//}