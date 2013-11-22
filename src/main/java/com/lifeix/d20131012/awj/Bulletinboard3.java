package com.lifeix.d20131012.awj;

public class Bulletinboard3 implements IObserver, IBulletinboard{

	private float temperature;
	private float humidity;
	private float pressure;
	
	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
	}

	@Override
	public void display() {
		System.out.println("==============预报============");
		System.out.println("温度："+temperature+" 湿度："+humidity+" 气压："+pressure);
		
	}

}
