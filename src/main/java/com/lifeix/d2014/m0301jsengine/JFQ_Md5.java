package com.lifeix.d2014.m0301jsengine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JFQ_Md5 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
		
		 ScriptEngineManager engineManager = new ScriptEngineManager();  
	        ScriptEngine engine = engineManager.getEngineByName("JavaScript"); 
	        engine.eval(new java.io.FileReader(new File(JFQ_Md5.class.getResource("md5.js").getFile())));  
	        Invocable inv = (Invocable)engine;  
	        Object a = inv.invokeFunction("md5", "123" );  
	        System.out.println(a.toString());
	}

}
