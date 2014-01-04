package com.lifeix.d20140104csdnbing;

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
			while(k<output.length && output[k]>=input[k]){
				if(output[k] > input[k]){
					k = output.length;
					break;
				}
				k++;
			}
			if(k < output.length){
				i--;
				j++;
				boolean out = false;
				if(!JI){
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
		}
		if(now > total)return new String("Impossible");
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
		if(total == now)return in;
		int[] on = in;
		int k = ((on.length+1)/2)-1;
		int j = k+1;
		if(on.length%10%2 > 0){
			j = k;
			on[k] ++;
			now ++;
		}else{
			on[k] ++;
			on[j] ++;
			now += 2;
		}
		boolean out = false;
		while(on[k] > 9){
			if(k<0){
				out = true;
				break;
			}
			on[k]=on[j] = 0;
			if(k == j){
				now -= on[k];
			}else{
				now -= (2*on[k]);
			}
			k--;
			j++;
			on[j]++;
			on[k]++;
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
		System.out.println(palindrom("11"));
	}
	
}
