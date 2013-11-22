package com.lifeix.d20130821;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ZDL_HelloInterface extends Remote {
	public String say() throws RemoteException;
}
