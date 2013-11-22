package com.lifeix.d20130821;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-21 下午06:39:16
 **/
public class JFQ_Hello extends UnicastRemoteObject implements
		JFQ_HelloInterface {

	private String message;
	  public JFQ_Hello (String msg) throws RemoteException {
	    message = msg;
	  }
	  public String say() throws RemoteException {
	    return message;
	  }

}

