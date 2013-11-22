package com.lifeix.d20130817;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @DESCRIPTION:	
 **/
public class JFQ_Line {
	
	/**@description:	
	 * @return:void
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFQ_Line jfq = new JFQ_Line();
		long start = System.currentTimeMillis();
		jfq.separateFile(new File("Z:/lifeix-growth-road.txt"), 100000, "d:/jfq/temp", 10, 8);
		long end = System.currentTimeMillis();
		System.out.println("总耗时：" + (end - start)/1000 + "S");
	}
	
	private void separateFile(File file, int lineNumber, String targetDirectory, int calCount,int times) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			int lineCount = 0;
			long count = 0;
			int fCount = 0;
			String line = "";
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine()) != null) {
				count++;
				if(times > 0) {
					++lineCount;
					if(lineCount == lineNumber) {
						sb.append(line);
						File temp = new File(targetDirectory);
						if(!temp.exists()) {
							temp.mkdirs();
						}
						File f = new File(targetDirectory + "/temp_" +(++fCount) + ".txt");
						if(!f.exists()) {
							f.createNewFile();
						}
						new WriteFileThread(sb.toString(), f).start();
						sb = new StringBuffer();
						lineCount = 0;
						if(fCount%calCount == 0) {
							new LineThread(targetDirectory, fCount - calCount + 1, fCount, times).start();
						}
	//					BufferedWriter bw = null;
	//					try {
	//						bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
	//						bw.write(sb.toString());
	//					} catch(Exception e) {
	//						e.printStackTrace();
	//					} finally {
	//						bw.close();
	//						lineCount = 0;
	//						sb = new StringBuffer();
	//						if(fCount%calCount == 0) {
	//							new LineThread(targetDirectory, fCount - calCount + 1, fCount, times).start();
	//						}
	//					}
					} else {
						sb.append(line).append("\n");
					}
				}
			}
			System.out.println("line = " + count);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class LineThread extends Thread {
		
		private String path;
		private long end;
		private int times;
		private int start;
		public LineThread(String path, int start, int end, int times) {
			this.path = path;
			this.end = end;
			this.start = start;
			if(times < 0) {
				times = 9;
			}
			this.times = times;
		}
		
		public void run() {
			BufferedReader br = null;
				for(int i = 0; i < times; i++) {
					System.out.println("times=" + i + ", end=" + end);
					for(int j = start; j <= end; j++) {
						try {
							br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path+"/temp_" + j + ".txt"))));
							while(br.readLine() != null) {
							}
						} catch(Exception e) {
							e.printStackTrace();
						} finally {
							try {
								br.close();
								br = null;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
		}
		
	}
	
	class WriteFileThread extends Thread {
		
		private String lines = "";
		private File f;
		public WriteFileThread(String lines, File f) {
			this.lines = lines;
			this.f = f;
		}
		
		public void run () {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
				bw.write(lines);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

