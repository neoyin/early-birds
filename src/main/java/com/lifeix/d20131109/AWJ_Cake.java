package com.lifeix.d20131109;

import java.io.Serializable;
/**
 * 蛋糕产品  
 * @author ANWJ
 *
 */
public class AWJ_Cake implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7265284397234853236L;

	private int cakeId;

	private String cakeName;
	
	private double price;
	
	public AWJ_Cake(int cakeId, String cakeName, double price){
		this.cakeId =  cakeId;
		this.cakeName = cakeName;
		this.price = price;
	}

	public int getCakeId() {
		return cakeId;
	}

	public void setCakeId(int cakeId) {
		this.cakeId = cakeId;
	}

	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

}
