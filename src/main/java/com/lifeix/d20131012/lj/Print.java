package com.lifeix.d20131012.lj;

public class Print {

	public static void print(Weather weather) {
		
	}
	public static void main(String[] args){
		Data.WEATHER = new Weather("晴", (float)15, (float)15, (float)15);
		Data.HISTORY = new Weather("", (float)0, (float)0, (float)0);
		Data.ID = 0;
		NumberGenerator generator = new RandomWeatherGenerator(); 

		Observer weather = new WeatherObserver();  
		Observer forecast = new ForecastObserver(); 
		Observer history = new HistoryObserver(); 
		
        generator.addObserver(weather);
        generator.addObserver(forecast);
        generator.addObserver(history);
          
        for(int i=0;i<10;i++){
        	System.out.println("---------------------------------------------------");
        	generator.change();
        	try {
				Thread.currentThread();
				Thread.sleep(1000 * 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
}
