package com.lifeix.d20130821;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class AWJ_HelloServer extends UnicastRemoteObject implements AWJ_HelloInterface {
	private String message;
	/**
	 * Construct a remote object
	 * @param msg the message of the remote object, such as "Hello, world!".
	 * @exception RemoteException if the object handle cannot be constructed.
	 */
	public AWJ_HelloServer (String msg) throws RemoteException {
		message = msg;
	}
	/**
	 * Implementation of the remotely invocable method.
	 * @return the message of the remote object, such as "Hello, world!".
	 * @exception RemoteException if the remote invocation fails.
	 */
	public String say() throws RemoteException {
		return message;
	}

	public static void main (String[] argv) {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind ("Hello", new AWJ_HelloServer ("Hello, world!"));
			System.out.println ("Hello Server is ready.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println ("Hello Server failed: " + e);
		}
	}
}