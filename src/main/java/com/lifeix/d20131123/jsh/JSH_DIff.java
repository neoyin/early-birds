package com.lifeix.jsh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.lifeix.jsh.diff_match_patch.Diff;

/**
 * call google's diff_match_patch to achieve diff small file content
 * @author lifeix
 *
 */
public class JSH_DIff {
	
	private static final String TEXT1_PATH = "/home/lifeix/MySrc/lifeix-growth-road/src/main/java/com/lifeix/jsh/11.txt";
	private static final String TEXT2_PATH = "/home/lifeix/MySrc/lifeix-growth-road/src/main/java/com/lifeix/jsh/22.txt";
	private static final String SHOWHTML_PATH = "/home/lifeix/MySrc/lifeix-growth-road/src/main/java/com/lifeix/jsh/diff.html";

	public static void main(String[] args) {
		JSH_DIff jsh = new JSH_DIff();
		String text1 = jsh.readFile(TEXT1_PATH);
		String text2 = jsh.readFile(TEXT2_PATH);
		String diff = jsh.diff_match(text1,text2);
		jsh.writeFile(diff, SHOWHTML_PATH);
	}
	/**
	 * diff text1 text2
	 * @param text1
	 * @param text2
	 */
	private String diff_match(String text1, String text2) {
		diff_match_patch dmp = new diff_match_patch();  
		LinkedList<Diff> df = dmp.diff_main(text1, text2);
		dmp.diff_cleanupSemantic(df);
		String ds = dmp.diff_prettyHtml(df);
		return ds;
	}
	/**
	 * read file content
	 * @param path
	 * @return
	 */
	private String readFile(String path) {
		StringBuffer content = new StringBuffer();
		BufferedReader br = null;
		try {
			File file = new File(path);
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	/**
	 * diff write diff.html
	 * @param diff
	 */
	private void writeFile(String diff, String path) {
		File file = new File(path);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(diff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
