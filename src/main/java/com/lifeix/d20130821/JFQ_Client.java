package com.lifeix.d20130821;

import java.rmi.Naming;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-21 下午07:10:39
 **/
public class JFQ_Client {
	 public static void main (String[] argv) {
		    try {
		      JFQ_HelloInterface hello = 
		        (JFQ_HelloInterface) Naming.lookup ("rmi://localhost:8888/Hello");
		      
		      System.out.println (hello.say());
		    } catch (Exception e) {
		      System.out.println ("HelloClient exception: " + e);
		    }
		  }
}

