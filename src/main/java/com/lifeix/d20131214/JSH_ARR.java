import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JSH_ARR {

	private Map<Integer,String> kv;
	public JSH_ARR() {
		kv = new HashMap<Integer,String>();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSH_ARR jsh = new JSH_ARR();
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int n = sc.nextInt();
		int m = sc.nextInt();
		for(int i = 0; i < m; i++) {
			boolean ret = jsh.isTure(sc.nextInt(), sc.nextInt());
			if(!ret) {
				System.out.println(-1);
				System.exit(-1);
			}
		}
		if(n == jsh.kv.keySet().size()) {
			System.out.println(1);
		} else {
			System.out.println("error paramters");
		}
	}
	/**
	 * get info and judge is true
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean isTure(int k, int v) {
		if(kv.get(k) == null) {
			if(kv.get(v) == null) {
				kv.put(k, "A");
				kv.put(v, "B");
			} else {
				if("A".equals(kv.get(v))) {
					kv.put(k, "B");
				} else {
					kv.put(k, "A");
				}
			}
		} else {
			if(kv.get(v) == null) {
				if("A".equals(kv.get(k))) {
					kv.put(v, "B");
				} else {
					kv.put(v, "A");
				}
			} else {
				if(kv.get(k).equals(kv.get(v))) {
					return false;
				}
			}
		}
		return true;
	}
}
