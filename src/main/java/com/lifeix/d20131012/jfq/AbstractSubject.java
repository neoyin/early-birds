package com.lifeix.d20131012.jfq;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {
	protected float tempor;   //温度
	protected float shi;      //湿度
	protected float as;       //气压
	
	private static List<IBlankObserver> list = new ArrayList<IBlankObserver>();
	
	public AbstractSubject(float tempor, float shi, float as) {
		this.tempor = tempor;
		this.shi = shi;
		this.as = as;
	}
	
	public void attachment(IBlankObserver bo) {
		list.add(bo);
	}
	
	public void deatachment(IBlankObserver bo) {
		list.remove(bo);
	}
	
	public void notifyObserver() {
		for(IBlankObserver ib : list) {
			ib.blank(tempor, shi, as);
		}
	}
	
	public abstract void push();
	
}
