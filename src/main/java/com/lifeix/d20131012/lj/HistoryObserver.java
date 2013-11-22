package com.lifeix.d20131012.lj;

public class HistoryObserver implements Observer{

	@Override
	public void update(NumberGenerator generator) {
		Weather weather = generator.getWeather().getHistory();
		StringBuilder builder = new StringBuilder();
		builder.append("天气统计：").append(weather.getWeather())
				.append("|平均温度：").append(weather.getTemperature())
				.append("|平均湿度").append(weather.getHumidity())
				.append("|平均气压").append(weather.getPress());
		System.out.println(builder.toString());
	}

}
