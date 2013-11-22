package com.lifeix.d20131012.awj;

public interface ISubject{
	//增加观察者
	public void addObs(IObserver o);
	//删除观察者
	public void deleteObs(IObserver o);
	//通知观察者
	public void notifyObs();
}