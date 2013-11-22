package com.lifeix.d20131012.zhch;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MeteorologicalData {
	private double temperature;
	private double humidity;
	private double pressure;
	private Date time;

	private static Random r = new Random();
	private static DecimalFormat format = new DecimalFormat("#0.00");

	public MeteorologicalData() {

	}

	public MeteorologicalData(double t, double h, double p, Date date) {
		temperature = t;
		humidity = h;
		pressure = p;
		time = date;
	}

	public static MeteorologicalData randomData() {

		double t = randomDouble();
		double h = randomDouble();
		double p = randomDouble();
		return new MeteorologicalData(t, h, p, new Date());
	}

	private static double randomDouble() {
		return 20 + r.nextInt(10) + Double.parseDouble(format.format(r.nextDouble()));
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public Date getTime() {
		return time;
	}

	public String getFormatTime() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
