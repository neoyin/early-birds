package com.lifeix.d20131012.lj;


public class Weather {

	private String weather;//天气
	private float humidity;//湿度
	private float temperature;//温度
	private float press;//气压
	private Weather history;
	public Weather(String weather, float humidity, float temperature, float press){
		this.weather = weather;
		this.humidity = humidity;
		this.temperature = temperature;
		this.press = press;
	}
	
	public void setHistory(String weather, float humidity, float temperature, float press) {
		this.history = new Weather();
		this.history.weather = weather;
		this.history.humidity = humidity;
		this.history.temperature = temperature;
		this.history.press = press;
	}
	public Weather(){};
	
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getPress() {
		return press;
	}
	public void setPress(float press) {
		this.press = press;
	}

	public Weather getHistory() {
		return history;
	}

}
