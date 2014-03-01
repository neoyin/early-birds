package com.lifeix.d2014.m0301jsengine;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AWJ_Md5 {

	public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
		
		 ScriptEngineManager engineManager = new ScriptEngineManager();  
	        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
	        Reader reader = new java.io.FileReader(new File(AWJ_Md5.class.getResource("md5.js").getFile()));
	        engine.eval(reader);  
	        Invocable invocable = (Invocable)engine;  
	        String s = (String)invocable.invokeFunction("md5", "123" );  
	        System.out.println(s);
	}

}
