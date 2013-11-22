package com.lifeix.d20131012.lj;

public class WeatherObserver implements Observer{

	@Override
	public void update(NumberGenerator generator) {
		Weather weather = generator.getWeather();
		StringBuilder builder = new StringBuilder();
		builder.append("当前天气：").append(weather.getWeather())
				.append("|温度：").append(weather.getTemperature())
				.append("|湿度").append(weather.getHumidity())
				.append("|气压").append(weather.getPress());
		System.out.println(builder.toString());
	}

}
