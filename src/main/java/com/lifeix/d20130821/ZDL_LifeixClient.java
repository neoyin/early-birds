package com.lifeix.d20130821;

import java.rmi.Naming;

public class ZDL_LifeixClient {

	  public static void main (String[] argv) {
		    try {
		      ZDL_HelloInterface hello = 
		        (ZDL_HelloInterface) Naming.lookup ("rmi://localhost:8888/LifeixServer");
		      System.out.println (hello.say());
		    } catch (Exception e) {
		      System.out.println ("HelloClient exception: " + e);
		    }
	  }
}