import java.io.BufferedInputStream;
import java.util.Scanner;

public class JSH_LargeIntegersMultiply {
	
	private static final int MARK = 1000;

	public static void main(String[] args) {
		JSH_LargeIntegersMultiply jsh = new JSH_LargeIntegersMultiply();
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		String numStr1 = sc.nextLine();
		String numStr2 = sc.nextLine();
		int[] num1 = jsh.getArray(numStr1);
		int[] num2 = jsh.getArray(numStr2);
		String result = jsh.Multiply(num1, num2);
		System.out.println(result);
	}
	private int[] getArray(String str) {
		int len = str.length();
		int length = len%3 == 0 ? len/3 : len/3+1;
		int[] num = new int[length];
		for(int i = len,j = length-1; i > 0; i=i-3,j--) {
			if(i-3 <= 0) {
				num[j] = Integer.parseInt(str.substring(0, i));
			} else {
				num[j] = Integer.parseInt(str.substring(i-3, i));
			}
		}
		return num;
	}
	private String Multiply(int[] num1, int[] num2) {
		int numlen1 = num1.length;
		int numlen2 = num2.length;
		int len = numlen1 + numlen2;
		int[] result = new int[len];
		int n = len - 1;
		for(int i = numlen1 - 1; i >= 0; i--) {
			int temp = num1[i];
			for(int j = numlen2 - 1; j >= 0; j--) {
				int retemp = result[n] + temp * num2[j];
				if(retemp >= MARK) {
					result[n] = retemp%MARK;
					result[n-1] += retemp/MARK;
				} else {
					result[n] = retemp;
				}
				n--;
			}
			n = n + numlen2 - 1;
		}
		StringBuffer sb = new StringBuffer();
		for(int i : result) {
			sb.append(i);
		}
		return sb.toString();
	}
}
