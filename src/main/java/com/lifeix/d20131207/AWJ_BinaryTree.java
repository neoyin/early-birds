package com.lifeix.d20131207;

public class AWJ_BinaryTree {
	
	private static String first = "";
	
	public static void main(String[] agrs) {
        String s1 = "42513";
        String s2 = "45231";
        cal_tree(s1, s2);
        if (first.length() != s1.length())
        {
            System.out.println("-1");
        }
        else {
            System.out.println(first);
        }
    }
    
    
	/**
	 * 根据中序序列和后序序列求出先序序列
	 * @param s1  中序序列
	 * @param s2  后序序列
	 */
    public static void cal_tree(String s1, String s2) {
    	
        boolean equals = strEquals(s1, s2);
        if (equals == false)
            return;
        if (s1.length() == 0)
            return;
        //后序序列中最后一个就是根
        char root = s2.charAt(s2.length()-1);
        //获取字符在中序序列总的位置
        int mid = s1.indexOf(root);
        //中序序列的左子树
        String leftTree=s1.substring(0, mid);
        //中序序列的右子树
        String rightTree = s1.substring(mid+1);
        //写入根
        first += String.valueOf(root);
        //中序左子树，后序左子树
        cal_tree(leftTree,s2.substring(0, leftTree.length()));
        //中序右子树，后序右子树,后序的右子树要最大为s2.length()-1
        cal_tree(rightTree,s2.substring(leftTree.length(),s2.length()-1));
        return;
    }
    
    /**
     * 判断中序和和后序是否包含相同字符串
     * @param s1
     * @param s2
     * @return
     */
    public static boolean strEquals(String s1, String s2) {
        boolean state = true;
        if (s1.length() != s2.length()) {
            state = false;
        }
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s2.indexOf(s1.charAt(i)) == -1)
                    state = false;
            }
        }
        return state;
    }

}
