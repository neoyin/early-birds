package com.lifeix.d2014.m0219instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JFQ_Inst {
	public static void premain(String agentArgs, Instrumentation inst){  
        inst.addTransformer(new MonitorTransformer());  
    }  
}

class MonitorTransformer implements ClassFileTransformer{

	private static final String prefix = "long start = System.currentTimeMillis(); \n";
	private static final String postifx = "long end = System.currentTimeMillis();\n";
	private static List<String> methods = new ArrayList<String>();
	
	static {
		methods.add("init");
		methods.add("work");
		methods.add("clearup");
	}
	
	@Override
	public byte[] transform(ClassLoader arg0, String arg1, Class<?> arg2,
			ProtectionDomain arg3, byte[] arg4)
			throws IllegalClassFormatException {
		if(arg1.endsWith("Serv")) {
			String className = arg1.replace("/", ".");
			CtClass cc = null;
			try {
				cc = ClassPool.getDefault().get(className);
				for(String method : methods) {
					CtMethod ctMethod = cc.getDeclaredMethod(method);
					String newMethodName = method + "$impl";
					ctMethod.setName(newMethodName);
					
					CtMethod newMethod = CtNewMethod.copy(ctMethod, method,cc, null);
					StringBuilder bodyStr = new StringBuilder();
					bodyStr.append("{\n");
					bodyStr.append(prefix);
					bodyStr.append(newMethodName+"($$);\n");
					bodyStr.append(postifx);
					bodyStr.append("System.out.println(\""+method+" : \" + (end - start) + \"ms\");\n");
					bodyStr.append("}");
					newMethod.setBody(bodyStr.toString());
					cc.addMethod(newMethod);
				}
	            return cc.toBytecode();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}


