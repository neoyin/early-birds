package com.lifeix.d2014.m0301jsengine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSH_ScriptTest {

	/**
	 * @param args
	 * @throws ScriptException 
	 * @throws FileNotFoundException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
		FileReader fileReader = new FileReader(new File("md5.js"));
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		engine.put("window", "getWindowBase()");
		engine.eval(fileReader);
		Invocable ivo = (Invocable)engine;
		String result = String.valueOf(ivo.invokeFunction("md5", "123"));
		System.out.println(result);
	}

}
