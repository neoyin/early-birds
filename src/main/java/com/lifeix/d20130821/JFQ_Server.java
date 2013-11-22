package com.lifeix.d20130821;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-21 下午07:11:07
 **/
public class JFQ_Server {
	 public static void main (String[] argv) {
		    try {
		    LocateRegistry.createRegistry(8888); 
		      Naming.rebind ("rmi://localhost:8888/Hello", new JFQ_Hello ("Hello, world!"));
		      System.out.println ("Hello Server is ready.");
		    } catch (Exception e) {
		      System.out.println ("Hello Server failed: " + e);
		    }
		  }
}

