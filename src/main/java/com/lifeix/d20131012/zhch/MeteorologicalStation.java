package com.lifeix.d20131012.zhch;

import java.util.ArrayList;
import java.util.List;

public class MeteorologicalStation {
	private List<ProcessMeteorological> subScribers;
	private boolean working;
	private MeteorologicalData nextData;
	
	public MeteorologicalStation(){
		subScribers = new ArrayList<ProcessMeteorological>();
	}
	/**
	 * 开机
	 */
	public void start() {
		working = true;
		nextData = MeteorologicalData.randomData();
		while(working){
			MeteorologicalData data = nextData;
			nextData = MeteorologicalData.randomData();
			for(ProcessMeteorological subScriber : subScribers){
				subScriber.processData(data, nextData);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 停机
	 */
	public void stop(){
		working = false;
	}
	
	public void addSubScriber(ProcessMeteorological subScriber){
		subScribers.add(subScriber);
	}
	public void removeSubScriber(ProcessMeteorological subScriber){
		subScribers.remove(subScriber);
	}

}
