package com.lifeix.d2014.m0219instrumentation.zhch;
import java.lang.instrument.Instrumentation;

/**
 * /early-birds/src/main/java/com/lifeix/d2014/m0219instrumentation/zhch/mymanifest
 * @author lifeix
 *
 */
public class Agent {
 
    public static void premain(String agentArgs, Instrumentation inst) {
         
        // registers the transformer
        inst.addTransformer(new Transformer());
    }
}
