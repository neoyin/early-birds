import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 甲乙两个人用一个英语单词玩游戏，两人轮流进行，每个人每次从中删掉任意一个字母，
 * 如果剩余的字母序列是严格单调递增的（按字典a<b<c<...<z），则这个人获得胜利。
 * 两个人都足够聪明，甲先开始，问他能赢吗？
 * 输入：一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单调递增的序列。
 * 输出：1表示甲可以赢，0表示甲不能赢。
 * 例如：输入bad，则甲可以删掉b或者a，剩余的是ad或者bd，他就赢了，输出1.
 * 又如：输入aaa，则甲只能删掉一个a，乙删掉一个a，剩余一个a，乙获胜，输出0.
 *
 */
public class Gaming {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gaming game = new Gaming();
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		String input = sc.nextLine();
		if(input.length() > 15) {
			System.out.println("input is too long");
			System.exit(1);
		}
		if(game.isWin(input)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	/**
	 * Is A win, if true and the result is yes, or no.
	 */
	private boolean isWin(String input) {
		int count = 0;
		int length = input.length();
		for(int i = 0; i < length-1; i++) {
			if(input.charAt(i) >= input.charAt(i+1)) {
				count++;
			}
		}
		if(count == 0) {
			System.out.println("input does not meet the requirements");
			System.exit(1);
			return true;
		} else if(count%2 == 0) {
			return false;
		} else {
			return true;
		}
	}
}
