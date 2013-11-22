package com.lifeix.d20130821;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ZDL_Helloimp extends UnicastRemoteObject implements ZDL_HelloInterface {
  private String message;
  public ZDL_Helloimp (String msg) throws RemoteException {
	  message=msg;
  }
@Override
public String say()throws RemoteException{
  return message;
}
}