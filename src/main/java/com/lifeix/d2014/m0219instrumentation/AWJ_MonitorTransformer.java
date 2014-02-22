package com.lifeix.d2014.m0219instrumentation;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class AWJ_MonitorTransformer implements ClassFileTransformer {

	final static String prefix ="\nlong startTime = System.currentTimeMillis();\n";
	final static String postfix ="\nlong endTime = System.currentTimeMillis();\n";
	final static List<String> methodList =new ArrayList<String>();
	static{
		methodList.add("com.lifeix.d2014.m0219instrumentation.Serv.init");
		methodList.add("com.lifeix.d2014.m0219instrumentation.Serv.work");
		methodList.add("com.lifeix.d2014.m0219instrumentation.Serv.clearup");
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain,byte[] classfileBuffer)
					throws IllegalClassFormatException {
		if(className.startsWith("com/lifeix/d2014/m0219instrumentation")){
			className = className.replace("/",".");
			CtClass ctclass = null;
			try {
				ctclass = ClassPool.getDefault().get(className);
				for(String method :methodList){
					if (method.startsWith(className)){
						String methodName = method.substring(method.lastIndexOf('.')+1, method.length());
						String outputStr ="\nSystem.out.println(\"this method "+methodName+" cost:\" +(endTime - startTime) +\"ms.\");";
						CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);
						String newMethodName = methodName +"$impl";
						ctmethod.setName(newMethodName);
						CtMethod newMethod = CtNewMethod.copy(ctmethod, methodName, ctclass,null);
						StringBuilder bodyStr =new StringBuilder();
						bodyStr.append("{");
						bodyStr.append(prefix); 
						bodyStr.append(newMethodName +"($$);\n"); 
						bodyStr.append(postfix);
						bodyStr.append(outputStr);
						bodyStr.append("}"); 
						newMethod.setBody(bodyStr.toString());
						ctclass.addMethod(newMethod); 
					}
				}    
				return ctclass.toBytecode();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CannotCompileException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
