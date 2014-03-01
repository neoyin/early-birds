package com.lifeix.d2014.m0301jsengine;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class LJApp {

	public static void main(String[] args) {
		try {
			LJApp test = new LJApp();
			test.md5("fjsdkf");
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

	public void md5(String s) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine jsEngine = sem.getEngineByName("javascript");
		try {
			jsEngine.eval(new FileReader("src/main/java/com/lifeix/d2014/m0301jsengine/md5.js"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		Invocable invocableEngine = (Invocable) jsEngine;
		try {
			Object ret = invocableEngine.invokeFunction("md5", s);
			System.out.println(ret);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
