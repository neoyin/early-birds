package com.lifeix.d20131207;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LJApp {

//	public void calculate(){
//		int n = 5;
//		String[] inorder = {"4","2","5","1","3"};
//		String[] postorder = {"4","5","2","3","1"};
//		HashMap<String,Tree> map = new HashMap<String, Tree>();
//		for(int i=0;i<inorder.length;i++){
//			Tree tree = new Tree();
//			tree.setSelf(inorder[i]);
//			map.put(inorder[i], tree);
//		}
//		String ancestry = inorder[0];
//		boolean find = false;
//		for(int i=0;i<inorder.length;i++){
//			for(int j=0;j<postorder.length;j++){
//				if(inorder[i] == postorder[j]){
//					if(j+1 == postorder.length){
//						find = true;
//						ancestry = postorder[j];
//						if(i+1<inorder.length){
//							map.get(inorder[i]).setRightChild(postorder[j-1]);
//						}
//					}
//				}else {
//					for(int k=0;k<i;k++){
//						if(inorder[k] == postorder[j+1]){
//							
//						}
//					}
//				}
//			}
//		}
//		check(map, ancestry);
//	}
	
	public boolean check(String[] inorder, String[] postorder, String ancestry, List<String> preorder){
		preorder.add(ancestry);
		int i=0;
		int kl=0;
		int kr=0;
		while(!inorder[i].equals(ancestry)){
			i++;
		}
		String[] inorderLeft = new String[i];
		String[] inorderRight = new String[inorder.length-i-1];
		for(int j=0;j<inorderLeft.length;j++){
			inorderLeft[j] = inorder[j];
		}
		for(int j=0;j<inorderRight.length;j++){
			inorderRight[j] = inorder[j+i+1];
		}
		for(int j=0;j<inorderLeft.length;j++){
			for(int l=0;l<postorder.length;l++){
				if(inorderLeft[j].equals(postorder[l])){
					if(l>kl)kl=l;
					break;
				}
			}
		}
		if(kl+1 != inorderLeft.length)return false;
		if(inorderLeft.length > 1){
			if(!check(inorderLeft, postorder, postorder[kl], preorder)){
				return false;
			}
		}else{
			if(inorderLeft.length > 0){
				preorder.add(inorderLeft[0]);
			}
		}
		for(int j=0;j<inorderRight.length;j++){
			for(int l=0;l<postorder.length;l++){
				if(inorderRight[j].equals(postorder[l])){
					if(l>kr)kr=l;
					break;
				}
			}
		}
		if(inorderRight.length > 1){
			if(!check(inorderRight, postorder, postorder[kr], preorder)){
				return false;
			}
		}else{
			if(inorderRight.length > 0){
				preorder.add(inorderRight[0]);
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		boolean go = true;
		while(go){
			int n = 0;
			Scanner scanner = new Scanner( System.in );
			n = Integer.parseInt(scanner.nextLine().trim());
			String s = scanner.nextLine();
			String[] inorder = s.split(" ");
			s = scanner.nextLine();
			String[] postorder = s.split(" ");
			if(n==inorder.length && inorder.length == postorder.length){
				LJApp app = new LJApp();
				List<String> preorder = new ArrayList<String>();
				try{
					boolean ok = app.check(inorder, postorder, postorder[postorder.length-1], preorder);
					if(ok){
						for(String str: preorder){
							System.out.print(str+",");
						}
					}else{
						System.out.println("-1");
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("继续？【yes/no】");
				String str = scanner.nextLine().trim();
				if(str.equals("no")){
					go = false;
				}
			}else{
				System.out.println("重新输入");
			}
		}
	}
}
