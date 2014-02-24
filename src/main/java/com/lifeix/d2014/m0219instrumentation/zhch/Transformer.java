package com.lifeix.d2014.m0219instrumentation.zhch;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
 
public class Transformer implements ClassFileTransformer {
 
    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
 
        byte[] byteCode = null;
 
        if (className.equals("com/lifeix/d2014/m0219instrumentation/Serv")) {
 
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get("com.lifeix.d2014.m0219instrumentation.Serv");
                moniterMethod(cc, "init");
                moniterMethod(cc, "work");
                moniterMethod(cc, "clearup");
                byteCode = cc.toBytecode();
                cc.detach();
            } catch (Exception ex) {
            	System.out.println("here err");
                ex.printStackTrace();
            }
        }
 
        return byteCode;
    }
    
    public void moniterMethod(CtClass cc, String name) throws NotFoundException, CannotCompileException{
            CtMethod m = cc.getDeclaredMethod(name);
            m.addLocalVariable("elapsedTime", CtClass.longType);
            m.insertBefore("elapsedTime = System.currentTimeMillis();");
            m.insertAfter("elapsedTime = System.currentTimeMillis() - elapsedTime;"
                    + "System.out.println(\"11 "+m.getName()+" : \" + elapsedTime);");
    }
}
