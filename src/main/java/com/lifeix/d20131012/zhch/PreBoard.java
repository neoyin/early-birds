package com.lifeix.d20131012.zhch;

public class PreBoard implements ProcessMeteorological {

	@Override
	public void processData(MeteorologicalData data, MeteorologicalData nextData) {
		System.out.println("下一阶段预报:");
		System.out.print("温度:" + nextData.getTemperature());
		System.out.print("  湿度:" + nextData.getHumidity());
		System.out.println("  气压:" + nextData.getPressure());
		System.out.println();
	}

}
