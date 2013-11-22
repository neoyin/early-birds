package com.lifeix.d20131012.awj;

public class Test {
	
	public static void main(String[] args) {
		
		WeatherData subject = new WeatherData();
		
		Bulletinboard1 ob1 = new Bulletinboard1();
		Bulletinboard3 ob2 = new Bulletinboard3();
		Bulletinboard2 ob3 = new Bulletinboard2();
		subject.addObs(ob1);
		subject.addObs(ob2);
		subject.addObs(ob3);
		subject.setWeatherData(13,34,56);
		ob1.display();
		ob2.display();
		ob3.display();
	}

}
