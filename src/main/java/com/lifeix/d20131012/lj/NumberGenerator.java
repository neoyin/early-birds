package com.lifeix.d20131012.lj;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class NumberGenerator {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer observer) {  
        observers.add(observer);  
    }  
    /** 删除观察者*/  
    public void delObserver(Observer observer) {  
        observers.remove(observer);  
    }  
    /** 通知所有观察者*/  
    public void notifyObservers() {  
        Iterator<Observer> it = observers.iterator();  
        while(it.hasNext()) {  
            Observer o =(Observer) it.next();  
            o.update(this);
        }  
    }  
    public abstract Weather getWeather();  
    public abstract void change();//气候变化
}
