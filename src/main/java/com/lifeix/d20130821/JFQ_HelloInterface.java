package com.lifeix.d20130821;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-21 下午06:38:13
 **/
public interface JFQ_HelloInterface extends Remote {
	public String say() throws RemoteException;
}	

