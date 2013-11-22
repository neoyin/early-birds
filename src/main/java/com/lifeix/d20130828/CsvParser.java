package com.lifeix.d20130828;

public interface CsvParser {
	String[] parse(String line) throws CsvException;
}
