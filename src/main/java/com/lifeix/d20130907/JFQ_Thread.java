package com.lifeix.d20130907;
/**
 * @DESCRIPTION:	
 * @DATE:2013-9-7 下午01:34:49
 **/
public class JFQ_Thread {

	private int count = 0;
	private int threadNum = 1;
	private boolean flag = true;
	
	/**@description:	
	 * @return:void
	 * @param args
	 */

	public static void main(String[] args) {
		JFQ_Thread t = new JFQ_Thread();
		new Thread(new ThreadPrint1(t)).start();
		new Thread(new ThreadPrint2(t)).start();
		new Thread(new ThreadPrint3(t)).start();
	}

	public  int getCount() {
		return count;
	}

	public  void setCount(int count) {
		this.count = count;
	}

	public  int getThreadNum() {
		return threadNum;
	}

	public  void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public  boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
}

class ThreadPrint1 implements Runnable {
	private JFQ_Thread t;
	public ThreadPrint1(JFQ_Thread t) {
		this.t = t;
	}
	
	public void run() {
		int count = 0;
		synchronized(t) {
			while(t.isFlag()) {
				t.notifyAll();
				if(t.getThreadNum() == 2 || t.getThreadNum() == 3) {
					try {
						t.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				if(t.getThreadNum() == 2 || t.getThreadNum() == 3) {
					continue;
				}
			    if(t.getCount() >= 75) {
			    	t.setFlag(false);
			    	break;
			    }
				if(count < 5) {
					++count;
					t.setCount(t.getCount() + 1);
					System.out.println(Thread.currentThread().getName() + ":" + t.getCount());
				} else {
					count=0;
					t.setThreadNum(2);
					System.out.println("\n\n");
				}
			}
			
		}
	}
	
}

class ThreadPrint2 implements Runnable {
	private JFQ_Thread t;
	public ThreadPrint2(JFQ_Thread t) {
		this.t = t;
	}
	
	public void run() {
		int count = 0;
		synchronized(t) {
			while(t.isFlag()) {
				t.notifyAll();
				if(t.getThreadNum() == 1 || t.getThreadNum() == 3) {
					try {
						t.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				if(t.getThreadNum() == 1 || t.getThreadNum() == 3) {
					continue;
				}
			    if(t.getCount() >= 75) {
			    	t.setFlag(false);
			    	break;
			    }
				if(count < 5) {
					++count;
					t.setCount(t.getCount() + 1);
					System.out.println(Thread.currentThread().getName() + ":" + t.getCount());
				} else {
					count=0;
					System.out.println("\n\n");
					t.setThreadNum(3);
				}
			}
		}
	}
	
}

class ThreadPrint3 implements Runnable {
	private JFQ_Thread t;
	public ThreadPrint3(JFQ_Thread t) {
		this.t = t;
	}
	
	public void run() {
		int count = 0;
		synchronized(t) {
			while(t.isFlag()) {
				t.notifyAll();
				if(t.getThreadNum() == 2 || t.getThreadNum() == 1) {
					try {
						t.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				if(t.getThreadNum() == 2 || t.getThreadNum() == 1) {
					continue;
				}
			    if(t.getCount() >= 75) {
			    	t.setFlag(false);
			    	break;
			    }
				if(count < 5) {
					++count;
					t.setCount(t.getCount() + 1);
					System.out.println(Thread.currentThread().getName() + ":" + t.getCount());
				} else {
					count=0;
					System.out.println("\n\n");
					t.setThreadNum(1);
					
				}
			}
		}
	}
	
}
