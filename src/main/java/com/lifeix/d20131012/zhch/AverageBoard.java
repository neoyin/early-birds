package com.lifeix.d20131012.zhch;

import java.util.ArrayList;
import java.util.List;

public class AverageBoard implements ProcessMeteorological {
	List<MeteorologicalData> datas;

	public AverageBoard() {
		datas = new ArrayList<MeteorologicalData>();
	}

	public MeteorologicalData getAverage() {
		double t = 0;
		double h = 0;
		double p = 0;
		for (MeteorologicalData data : datas) {
			t += data.getTemperature();
			h += data.getHumidity();
			p += data.getPressure();
		}
		t = t / datas.size();
		h = h / datas.size();
		p = p / datas.size();
		return new MeteorologicalData(t, h, p, null);
	}

	@Override
	public void processData(MeteorologicalData data, MeteorologicalData nextData) {
		datas.add(data);
		if (datas.size() > 15) {
			datas.remove(0);
		}
		MeteorologicalData avaData = getAverage();
		System.out.println("最近几天平均温度:" + avaData.getTemperature() + " 平均湿度:" + avaData.getHumidity() + "  平均气压:"
				+ avaData.getPressure());
		System.out.println();
	}

}
