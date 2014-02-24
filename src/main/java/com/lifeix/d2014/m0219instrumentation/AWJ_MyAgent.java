package com.lifeix.d2014.m0219instrumentation;

import java.lang.instrument.Instrumentation;
 
public class AWJ_MyAgent {
   
    public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new AWJ_MonitorTransformer());
    }
  
}
