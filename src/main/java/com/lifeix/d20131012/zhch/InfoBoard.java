package com.lifeix.d20131012.zhch;

public class InfoBoard implements ProcessMeteorological {

	@Override
	public void processData(MeteorologicalData data, MeteorologicalData nextData) {
		System.out.println("时间:" + data.getFormatTime());
		System.out.print("温度:" + data.getTemperature());
		System.out.print("  湿度:" + data.getHumidity());
		System.out.println("  气压:" + data.getPressure());
		System.out.println();
	}

}
