package com.lifeix.d20130821;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AWJ_HelloInterface extends Remote {
	/**
	 * Remotely invocable method.
	 * @return the message of the remote object, such as "Hello, world!".
	 * @exception RemoteException if the remote invocation fails.
	 */
	public String say() throws RemoteException;
}
