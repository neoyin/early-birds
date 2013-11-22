package com.lifeix.d20130828;

public class CsvException extends Exception{
	private static final long serialVersionUID = 7119084563870217227L;

	public CsvException(){
		super("错误的CSV格式.");
	}
}
