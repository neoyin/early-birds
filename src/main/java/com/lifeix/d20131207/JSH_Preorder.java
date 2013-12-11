package com.lifeix.jsh;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class JSH_Preorder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSH_Preorder jsh = new JSH_Preorder();
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int length = Integer.parseInt(sc.nextLine());
		int[] in = jsh.getParam(sc.nextLine(), length);
		int[] post = jsh.getParam(sc.nextLine(), length);
		TreeNode root = jsh.buildTree(in, post, 0, length-1, length);
		jsh.preTreeNode(root);
	}
	/**
	 * static TreeNode
	 */
	static class TreeNode {
		int node;
		TreeNode lchild;
		TreeNode rchild;
		public TreeNode(int node) {
			this.node = node;
		}
	}
	/**
	 * get TreeNode
	 * @param in
	 * @param post
	 * @param start
	 * @param end
	 * @param length
	 * @return
	 */
	private TreeNode buildTree(int[] in, int[] post, int start, int end, int length) {
		if(in == null || in.length == 0 || post == null || post.length == 0 || length <= 0) {
			return null;
		}
		TreeNode root = new TreeNode(post[end]);
		int i = 0;
		while(i < length) {
			if(root.node == in[end - i]) {
				break;
			}
			i++;
		}
		if(i > end) {
			System.out.println(-1);
			System.exit(1);
		}
		//终止递归
		if(length == 1) {
			return root;
		}
		root.lchild = buildTree(in, post, start, end-i-1, length-i-1);
		root.rchild = buildTree(in, post, start+i+1, end-1, i);
		return root;
	}
	/**
	 * preorder TreeNode
	 * @param root
	 */
	private void preTreeNode(TreeNode root) {
		if(root != null) {
			System.out.print(root.node + " ");
			preTreeNode(root.lchild);
			preTreeNode(root.rchild);
		}
	}
	/**
	 * get params
	 * @param lineParam
	 * @param length
	 * @return
	 */
	private int[] getParam(String lineParam, int length) {
		int[] param = new int[length];
		String[] p = lineParam.split(" ");
		for(int i = 0; i < length; i++) { 
			param[i] = Integer.parseInt(p[i]);
		}
		return param;
	}
}
