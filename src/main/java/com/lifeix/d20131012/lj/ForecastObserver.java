package com.lifeix.d20131012.lj;

import java.util.Random;

public class ForecastObserver implements Observer{

	@Override
	public void update(NumberGenerator generator) {
		Weather weather = this.getForecast(generator.getWeather());
		StringBuilder builder = new StringBuilder();
		builder.append("未来天气：").append(weather.getWeather())
				.append("|温度：").append(weather.getTemperature())
				.append("|湿度").append(weather.getHumidity())
				.append("|气压").append(weather.getPress());
		System.out.println(builder.toString());
	}
	
	public Weather getForecast(Weather w){
		Random random = new Random();
		String weather = w.getWeather();
		float humidity = w.getHumidity() + random.nextFloat();
		float temperature = w.getTemperature() + random.nextFloat();
		float press = w.getPress() + random.nextFloat();
		return new Weather(weather, humidity, temperature, press);
	}

}
