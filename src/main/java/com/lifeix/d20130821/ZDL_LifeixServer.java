package com.lifeix.d20130821;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ZDL_LifeixServer {
public static void main(String[] args) {
	try {
		 LocateRegistry.createRegistry(8888); 
		 Naming.bind("rmi://localhost:8888/LifeixServer",new ZDL_Helloimp("helloword")); 
		  System.out.println ("Hello Server is ready.");
	} catch (AlreadyBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
