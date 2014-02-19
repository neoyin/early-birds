package com.lifeix.d20140104csdnbing;

import java.util.Random;


public class LJApp {

	public static String palindrom(String a){
		boolean JI = false;
		int[] input = new int[a.length()];
		int total = 0;
		for(int i=input.length-1;i>=0;i--){
			input[i] = Integer.parseInt(a.substring(i, i+1));
			total += input[i];
		}
		if((total%10)%2>0){
			JI = true;
		}
		int[] output = null;
		int now = 0;
		if(input.length == 1){
			output = new int[3];
			output[0] = 1;
			output[1] = 0;
			output[2] = 1;
			now = 2;
			if(now > total)return new String("Impossible");
		}else if(!JI || (input.length%10)%2>0){
			output = new int[input.length];
			int i=0;
			int j=input.length-1;
			while(j>=i){
				output[j] = output[i] = input[i];
				if(i==j){
					now += input[i];
				}else{
					now += (2*input[i]);
				}
				i++;
				j--;
			}
			int k = j+1;
			boolean big = false;
			while(k<output.length && output[k]>=input[k]){
				if(output[k] > input[k]){
					big = true;
					break;
				}
				k++;
			}
			if(!big){
				i--;
				j++;
				boolean out = false;
				if(i!=j){
					output[j]++;
					output[i]++;
					now += 2;
				}else{
					output[i]++;
					now ++;
				}
				while(output[i] > 9){
					if(i<0){
						out = true;
						break;
					}
					output[j]=output[i] = 0;
					if(i == j){
						now -= output[i];
					}else{
						now -= (2*output[i]);
					}
					i--;
					j++;
					output[j] = (output[i]++);
				}
				if(out){
					output = new int[output.length+2];
					output[0] = 1;
					output[output.length-1] = 1;
					for(int l=1;l<output.length-1;l++){
						output[l] = 0;
					}
					now = 2;
				}
			}
		}else{
			output = new int[input.length+1];
			output[0] = 1;
			output[output.length-1] = 1;
			for(int i=1;i<output.length-1;i++){
				output[i] = 0;
			}
			now = 2;
		}
		output = find(output, now, total, JI);
		if(output != null && output.length > 0){
			StringBuilder builder = new StringBuilder();
			for(int o : output){
				builder.append(o);
			}
			return builder.toString();
		}else{
			return new String("Impossible");
		}
	}
	
	public static int[] find(int[] in, int now, int total, boolean JI){
		int[] on = in;
		int k = ((on.length+1)/2)-1;
		int j = k+1;
		boolean ji = false;
		boolean out = false;
		if(on.length%10%2 > 0){
			ji = true;
			j = k;
		}
		if(total == now)return on;
		if(ji){
			if((total-now) / 9 > 1){
				on[k] += 9;
				now += 9;
			}else{
				on[k] ++;
				now ++;
			}
		}else{
			if((total-now) / 9 >2){
				on[k] += 9;
				on[j] += 9;
				now += 18;
			}else{
				on[k] ++;
				on[j] ++;
				now += 2;
			}
		}
		out = false;
		while(on[k] > 9){
			if(k<=0){
				out = true;
				break;
			}
			if(k == j){
				now -= on[k];
			}else{
				now -= (2*on[k]);
			}
			on[k]=on[j] = 0;
			k--;
			j++;
			if((total-now) / 9 >2){
				on[j] += 9;
				on[k] += 9;
				now += 18;
			}else{
				on[j]++;
				on[k]++;
				now += 2;
			}
		}
		if(out){
			if(JI){
				on = new int[on.length+2];
			}else{
				on = new int[on.length+1];
			}
			on[0] = 1;
			on[on.length-1] = 1;
			for(int l=1;l<on.length-1;l++){
				on[l] = 0;
			}
			now = 2;
			if(now > total)return null;
		}
		return find(on, now, total, JI);
	}
	public static void main(String[] args){
//		Random random = new Random();
//		StringBuilder builder = new StringBuilder();
//		for(int i=0;i<801;i++){
//			builder.append(random.nextInt(9));
//		}
//		System.out.println(palindrom(builder.toString()));
		System.out.println(palindrom("1151111111111111111111111111111111111111111111111112"));
	}
	
}
