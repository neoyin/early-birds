package com.lifeix.d20130907;


public class ZDL_AddNumMain  extends Thread{
	One one =null;
	Two two =null;
	Three three=null;
	SayNum sayNum =null;
	class SayNum{
		int num;
		int x=1;
		public void printNum(Thread thread) {
			for(int n= 1;n<6;n++){
				if(x>75){
					break;
				}
				System.out.println(thread.getName()+"-----"+(x++));
			}
		}
	}
	public static void main(String[] args) {
		ZDL_AddNumMain addNumMain=new ZDL_AddNumMain();
		addNumMain.printNum();
	}
	void printNum() {
		sayNum=new SayNum(); 
		one = new One();
		two = new Two();
		three = new Three();
		sayNum.num = 1;
		one.start();
		two.start();
		three.start();
	}
	class One extends Thread{
		@Override
		public void run() {
			while(true){
				synchronized (sayNum) {
					if(sayNum.num!=1){
						try {
							sayNum.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(sayNum.num>75){
						sayNum.notifyAll();
						System.exit(0);
					}
					if(sayNum.num!=1){
						continue;
					}
					sayNum.printNum(this.currentThread());
					sayNum.num = 2;
					sayNum.notifyAll();
				}
			}
		}
	}
	class Two extends Thread{
		@Override
		public void run() {
			synchronized (sayNum) {
				while(true){
					if(sayNum.num!=2){
						try {
							sayNum.notifyAll();
							sayNum.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(sayNum.num>75){
						sayNum.notifyAll();
						System.exit(0);
					}
					if(sayNum.num!=2){
						continue;
					}
					sayNum.printNum(this.currentThread());
					sayNum.num = 3;
					sayNum.notifyAll();

				}
			}
		}
	}
	class Three extends Thread{
		@Override
		public void run() {

			synchronized (sayNum) {	
				while(true){
					if(sayNum.num!=3){
						try {
							sayNum.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(sayNum.num>75){
						sayNum.notifyAll();
						System.exit(0);
					}
					if(sayNum.num!=3){
						continue;
					}
					sayNum.printNum(this.currentThread());
					sayNum.num = 1;
					sayNum.notifyAll();
				}
			}
		}
	}
}
