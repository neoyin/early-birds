package com.lifeix.d20131012.awj;

public class Bulletinboard1 implements IObserver, IBulletinboard{

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
		System.out.println("============天气状况==========");
		System.out.println("温度："+temperature+" 湿度："+humidity+" 气压："+pressure);
		
	}

}
