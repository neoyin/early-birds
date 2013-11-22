package com.lifeix.d20131012.awj;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements ISubject{
	private float temperature;
	private float humidity;
	private float pressure;

	private List<IObserver> myList = new ArrayList<IObserver>();

	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	@Override
	public void addObs(IObserver o) {

		myList.add(o);

	}
	@Override
	public void deleteObs(IObserver o) {

		myList.remove(o);

	}
	@Override
	public void notifyObs() {

		for(IObserver ob : myList){
			ob.update(temperature, humidity, pressure);
		}
	}

	public void setWeatherData(float paramTemp, float paramHumidity, float paramPressure)
	{
		this.temperature = paramTemp;
		this.humidity = paramHumidity;
		this.pressure = paramPressure;
		weachterChanged();
	}

	public void weachterChanged()
	{
		this.notifyObs();
	}


}
