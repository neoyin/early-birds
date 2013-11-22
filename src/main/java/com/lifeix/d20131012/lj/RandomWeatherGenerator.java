package com.lifeix.d20131012.lj;

import java.util.Random;

public class RandomWeatherGenerator extends NumberGenerator {

	private Weather weather;

	public void change() {
		Random random = new Random();
		weather = new Weather(Data.WEATHER.getWeather(),
				Data.WEATHER.getHumidity()+random.nextFloat(),
				Data.WEATHER.getTemperature()+random.nextFloat(),
				Data.WEATHER.getPress()+random.nextFloat());
		Data.HISTORY = new Weather(weather.getWeather(),
						(Data.HISTORY.getHumidity()*Data.ID + weather.getHumidity())/(Data.ID+1),
						(Data.HISTORY.getTemperature()*Data.ID + weather.getTemperature())/(Data.ID+1),
						(Data.HISTORY.getPress()*Data.ID + weather.getPress())/(Data.ID+1));
		weather.setHistory(Data.HISTORY.getWeather(), Data.HISTORY.getHumidity(), Data.HISTORY.getTemperature(), Data.HISTORY.getPress());
		Data.ID++;
		notifyObservers();
	}

	public Weather getWeather() {
		return weather;
	}

}
