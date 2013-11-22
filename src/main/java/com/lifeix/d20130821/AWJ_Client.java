package com.lifeix.d20130821;

import java.rmi.Naming;

public class AWJ_Client {
	
	public static void main (String[] argv) {
	    try {
	      AWJ_HelloInterface hello = 
	        (AWJ_HelloInterface) Naming.lookup ("rmi://localhost:1099/Hello");
	      System.out.println (hello.say());
	    } catch (Exception e) {
	      System.out.println ("HelloClient exception: " + e);
	    }
	  }

}
