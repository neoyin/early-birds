package com.lifeix.d20131102;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeix.tool.useragent.UserAgent;
import com.lifeix.tool.useragent.UserAgentTool;

public class LJApp {
	public static Map<String, Browser> browser = new HashMap<String, Browser>();
	public static List<Browser> browserList = new ArrayList<Browser>();
	public static Map<String, Operating> operating = new HashMap<String, Operating>();
	public static List<Operating> operatingList = new ArrayList<Operating>();
	public static Map<String, Ip> ip = new HashMap<String, Ip>();
	public static List<Ip> ipList = new ArrayList<Ip>();
	public static List<Ip> ipLast = new ArrayList<Ip>();
	public static Map<String, Ip> ipL = new HashMap<String, Ip>();
	public static Map<String, Code> code = new HashMap<String, Code>();
	public static List<Code> codeList = new ArrayList<Code>();
	public static Map<String, Page> page = new HashMap<String, Page>();
	public static List<Page> pageList = new ArrayList<Page>();

	public static UserAgentTool tool = new UserAgentTool();

	public static List<Thread> tNum = new ArrayList<Thread>();

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			readNew();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// while(browserList.size() > 1){
		// int k = 0;
		// int n = browserList.get(0).getNumber();
		// for(int i=1;i<browserList.size();i++){
		// if(browserList.get(i).getNumber() > n){
		// k = i;
		// n = browserList.get(i).getNumber();
		// }
		// }
		// System.out.println(browserList.get(k).getName()+"|"+n);
		// browserList.remove(k);
		// }
		// System.out.println(browserList.get(0).getName()+"|"+browserList.get(0).getNumber());
		//
		while (ipLast.size() > 1) {
			int k = 0;
			int n = ipLast.get(0).getNumber();
			for (int i = 1; i < ipLast.size(); i++) {
				if (ipLast.get(i).getNumber() > n) {
					k = i;
					n = ipLast.get(i).getNumber();
				}
			}
			if (n < 2)
				break;
			System.out.println(ipLast.get(k).getIp() + "|" + n);
			ipLast.remove(k);
		}
		System.out.println(ipLast.get(0).getIp() + "|"
				+ ipLast.get(0).getNumber());
		
		 while(operatingList.size() > 1){
		 int k = 0;
		 int n = operatingList.get(0).getNumber();
		 for(int i=1;i<operatingList.size();i++){
		 if(operatingList.get(i).getNumber() > n){
		 k = i;
		 n = operatingList.get(i).getNumber();
		 }
		 }
		 System.out.println(operatingList.get(k).getName()+"|"+n);
		 operatingList.remove(k);
		 }
		 System.out.println(operatingList.get(0).getName()+"|"+operatingList.get(0).getNumber());
		
		 while(codeList.size() > 1){
		 int k = 0;
		 int n = codeList.get(0).getNumber();
		 for(int i=1;i<codeList.size();i++){
		 if(codeList.get(i).getNumber() > n){
		 k = i;
		 n = codeList.get(i).getNumber();
		 }
		 }
		 System.out.println(codeList.get(k).getCode()+"|"+n);
		 codeList.remove(k);
		 }
		 System.out.println(codeList.get(0).getCode()+"|"+codeList.get(0).getNumber());
		
		 while(pageList.size() > 1){
		 int k = 0;
		 int n = pageList.get(0).getNumber();
		 for(int i=1;i<pageList.size();i++){
		 if(pageList.get(i).getNumber() > n){
		 k = i;
		 n = pageList.get(i).getNumber();
		 }
		 }
		 if(n<=1)break;
		 System.out.println(pageList.get(k).getPageId()+"|"+n);
		 pageList.remove(k);
		 }
		 System.out.println(pageList.get(0).getPageId()+"|"+pageList.get(0).getNumber());
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000 + "s");
	}

	// public static synchronized void addIp(List<Ip> ips){
	// ipList.add(ips);
	// }

	public static void readNew() throws IOException {
		File f = new File("/home/abc/access.www.l99.com_20131101.log");
		BufferedInputStream fis = new BufferedInputStream(
				new FileInputStream(f));
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis,
				"utf-8"), 1 * 1024 * 1024);// 用5M的缓冲读取文本文件

		String line;
		int i = 0;
		List<String> lines = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {

			if (i % 10000 == 0) {
				resolve(lines);
				// List<String> l = new ArrayList<String>();
				// l.addAll(lines);
				// resolve.setLine(l);
				// Thread t = new Thread(resolve);
				// t.start();
				// lines.clear();
				// tNum.add(t);
				//
				Runtime currRuntime = Runtime.getRuntime();
				int nFreeMemory = (int) (currRuntime.freeMemory() / 1024 / 1024);
				int nTotalMemory = (int) (currRuntime.totalMemory() / 1024 / 1024);
				System.out.println(i + "|" + nFreeMemory + "/" + nTotalMemory);
			}
			lines.add(line);
			i++;
		}
		for (int n = 0; n < tNum.size(); n++) {
			try {
				tNum.get(n).join();
			} catch (InterruptedException e) {
			}
		}

	}

	public static void resolve(List<String> str) {
		List<String> ipOnes = new ArrayList<String>();
		List<String> pageOnes = new ArrayList<String>();
		List<String> codeOnes = new ArrayList<String>();
		List<String> browserOnes = new ArrayList<String>();
		List<String> operatingOnes = new ArrayList<String>();
		for(int i=0;i<str.size();i++){
			UserAgent agent = new UserAgent(str.get(i));
			String[] s = str.get(i).split(" ");
			ipOnes.add(s[0]);
			pageOnes.add(s[6]);
			codeOnes.add(s[8]);
			browserOnes.add(agent.getBrowser().toString());
			operatingOnes.add(agent.getOperatingSystem().toString());
		}

		AddBrowser b = new AddBrowser();
		b.setBrowserOne(browserOnes);
		AddOperating o = new AddOperating();
		o.setOperatingOne(operatingOnes);
		AddIp i = new AddIp();
		i.setIpOne(ipOnes);
		AddCode c = new AddCode();
		c.setCodeOne(codeOnes);
		AddPage p = new AddPage();
		p.setPageOne(pageOnes);
		Thread t1 = new Thread(b);
		Thread t2 = new Thread(o);
		Thread t3 = new Thread(i);
		Thread t4 = new Thread(c);
		Thread t5 = new Thread(p);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {
		}

	}
}

class Resolve implements Runnable {
	private List<String> ipOne;

	public List<String> getIpOne() {
		return ipOne;
	}

	public void setIpOne(List<String> ipOne) {
		this.ipOne = ipOne;
	}

	@Override
	public void run() {
		for(int k=0;k<ipOne.size();k++){
			if (LJApp.ip.containsKey(ipOne.get(k))) {
				LJApp.ip.get(ipOne.get(k)).setNumber(LJApp.ip.get(ipOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.ipList.size(); i++) {
//					if (LJApp.ipList.get(i).getIp().equals(ipOne)) {
//						LJApp.ipList.get(i).setNumber(
//								LJApp.ipList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Ip b = new Ip();
				b.setIp(ipOne.get(k));
				LJApp.ip.put(ipOne.get(k), b);
			}
		}
	}


}

class AddBrowser implements Runnable {
	private List<String> browserOne;

	public List<String> getBrowserOne() {
		return browserOne;
	}

	public void setBrowserOne(List<String> browserOne) {
		this.browserOne = browserOne;
	}

	@Override
	public void run() {
		for(int k=0;k<browserOne.size();k++){
			if (LJApp.browser.containsKey(browserOne.get(k))) {
				LJApp.browser.get(browserOne.get(k)).setNumber(LJApp.browser.get(browserOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.browserList.size(); i++) {
//					if (LJApp.browserList.get(i).getName().equals(browserOne.get(k))) {
//						LJApp.browserList.get(i).setNumber(
//								LJApp.browserList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Browser b = new Browser();
				b.setName(browserOne.get(k));
				LJApp.browser.put(browserOne.get(k), b);
			}
		}
	}

}

class AddIp implements Runnable {
	private List<String> ipOne;

	public List<String> getIpOne() {
		return ipOne;
	}

	public void setIpOne(List<String> ipOne) {
		this.ipOne = ipOne;
	}

	@Override
	public void run() {
		for(int k=0;k<ipOne.size();k++){
			if (LJApp.ip.containsKey(ipOne.get(k))) {
				LJApp.ip.get(ipOne.get(k)).setNumber(LJApp.ip.get(ipOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.ipList.size(); i++) {
//					if (LJApp.ipList.get(i).getIp().equals(ipOne.get(k))) {
//						LJApp.ipList.get(i).setNumber(
//								LJApp.ipList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Ip b = new Ip();
				b.setIp(ipOne.get(k));
				LJApp.ip.put(ipOne.get(k), b);
			}
		}
	}


}

class AddOperating implements Runnable {
	private List<String> operatingOne;

	public List<String> getOperatingOne() {
		return operatingOne;
	}

	public void setOperatingOne(List<String> operatingOne) {
		this.operatingOne = operatingOne;
	}

	@Override
	public void run() {
		for(int k=0;k<operatingOne.size();k++){
			if (LJApp.operating.containsKey(operatingOne.get(k))) {
				LJApp.operating.get(operatingOne.get(k)).setNumber(LJApp.operating.get(operatingOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.operatingList.size(); i++) {
//					if (LJApp.operatingList.get(i).getName().equals(operatingOne.get(k))) {
//						LJApp.operatingList.get(i).setNumber(
//								LJApp.operatingList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Operating b = new Operating();
				b.setName(operatingOne.get(k));
				LJApp.operating.put(operatingOne.get(k), b);
			}
		}

	}

}

class AddCode implements Runnable {
	private List<String> codeOne;

	public List<String> getCodeOne() {
		return codeOne;
	}

	public void setCodeOne(List<String> codeOne) {
		this.codeOne = codeOne;
	}

	@Override
	public void run() {
		for(int k=0;k<codeOne.size();k++){
			if (LJApp.code.containsKey(codeOne.get(k))) {
				LJApp.code.get(codeOne.get(k)).setNumber(LJApp.code.get(codeOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.codeList.size(); i++) {
//					if (LJApp.codeList.get(i).getCode().equals(codeOne.get(k))) {
//						LJApp.codeList.get(i).setNumber(
//								LJApp.codeList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Code b = new Code();
				b.setCode(codeOne.get(k));
				LJApp.code.put(codeOne.get(k), b);
			}
		}

	}

}

class AddPage implements Runnable {
	private List<String> pageOne;

	public List<String> getPageOne() {
		return pageOne;
	}

	public void setPageOne(List<String> pageOne) {
		this.pageOne = pageOne;
	}

	@Override
	public void run() {
		for(int k=0;k<pageOne.size();k++){
			if (LJApp.page.containsKey(pageOne.get(k))) {
				LJApp.page.get(pageOne.get(k)).setNumber(LJApp.page.get(pageOne.get(k)).getNumber() + 1);
//				for (int i = 0; i < LJApp.pageList.size(); i++) {
//					if (LJApp.pageList.get(i).getPageId().equals(pageOne.get(k))) {
//						LJApp.pageList.get(i).setNumber(
//								LJApp.pageList.get(i).getNumber() + 1);
//					}
//				}
			} else {
				Page b = new Page();
				b.setPageId(pageOne.get(k));
				LJApp.page.put(pageOne.get(k), b);
			}
		}
	}

}

// 浏览器
class Browser {
	private String name;
	private int number = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

class Operating {
	private String name;
	private int number = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

class Ip {
	private String ip;
	private int number = 1;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

class Page {
	private String pageId;
	private int number = 1;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

class Code {
	private String code;
	private int number = 1;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
