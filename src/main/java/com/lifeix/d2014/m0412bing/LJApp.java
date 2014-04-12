package com.lifeix.d2014.m0412bing;

public class LJApp {
	static char[] ch;
    static int index=0;
    static int b_num=0;
    static int i_num=0;
    static int n_num=0;
    static int num=0;
     
    public static void main(String[] args) {
         
        System.out.println(getNum("iinbinbinginng"));
    }
     
    public static int getNum(String str){
        ch=str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            switch (ch[i]) {
            case 'b':
                b_num++;
                break;
            case 'i':
                i_num+=b_num;
                break;
            case 'n':
                n_num+=i_num;
                break;
            case 'g':
                num+=n_num;
                break;
            default:
                break;
            }
        }
        return num;
    }
}
