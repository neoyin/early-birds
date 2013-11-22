package com.lifeix.d20131012;

import java.util.ArrayList;
import java.util.List;

abstract class Subject {
	/**
	 * store Observer object
	 */
	private List<Observer> list = new ArrayList<Observer>();
	/**
	 * register Observer
	 * @param obs
	 */
	public void initObserver(Observer obs) {
		list.add(obs);
	}
	/**
	 * register Observers
	 * @param obs
	 */
	public void initObservers(Observer... obs) {
		for(Observer observer : obs) {
			initObserver(observer);
		}
	}
	/**
	 * remove Observer
	 * @param obs
	 */
	public void removeObserver(Observer obs) {
		list.remove(obs);
	}
	/**
	 * nodify Observer
	 */
	public void nodifyObserver() {
		for(Observer obs : list) {
			obs.update(this);
		}
	}
	public abstract void changed(float temperature,float humidity,float airPressure);
}

interface Observer {
	/**
	 * modify oneself according to Subject
	 * @param subject
	 */
	public abstract void update(Subject subject);
}

class WeatherStations extends Subject {
	private float temperature = 0.0f;
	private float humidity = 0.0f;
	private float airPressure = 0.0f;
	
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
	public float getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(float airPressure) {
		this.airPressure = airPressure;
	}
	@Override
	public void changed(float temperature, float humidity, float airPressure) {
		// TODO Auto-generated method stub
		this.setTemperature(temperature);
		this.setHumidity(humidity);
		this.setAirPressure(airPressure);
		this.nodifyObserver();
	}
}
class WeatherConditionsDisplay implements Observer {

	@Override
	public void update(Subject subject) {
		WeatherStations data = (WeatherStations)subject;
		System.out.println("show weather conditions now");
		System.out.println("temperature:"+data.getTemperature()+"|humidity:"+data.getHumidity()+"|airPressure:"+data.getAirPressure());
	}
	
}
class MeteorologicalStatistics implements Observer {
	
	@Override
	public void update(Subject subject) {
		WeatherStations data = (WeatherStations)subject;
		System.out.println("show meteorological statistics");
		System.out.println("temperature:"+data.getTemperature()+"|humidity:"+data.getHumidity()+"|airPressure:"+data.getAirPressure());
	}
	
}
class Forecast implements Observer {
	
	@Override
	public void update(Subject subject) {
		WeatherStations data = (WeatherStations)subject;
		System.out.println("show simple forecast");
		System.out.println("temperature:"+data.getTemperature()+"|humidity:"+data.getHumidity()+"|airPressure:"+data.getAirPressure());
	}
	
}

public class JSH_ObserverModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WeatherStations weatherStations = new WeatherStations();
		Observer weatherConditions = new  WeatherConditionsDisplay();
		Observer MeteorologicalStatistics = new  MeteorologicalStatistics();
		Observer Forecast = new  Forecast();
		weatherStations.initObservers(weatherConditions,MeteorologicalStatistics,Forecast);
		weatherStations.changed(1.2f, 3.1f, 9.7f);
	}

}
