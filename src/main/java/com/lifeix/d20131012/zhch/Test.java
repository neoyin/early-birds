package com.lifeix.d20131012.zhch;

public class Test {
	public static void main(String[] args) {
		MeteorologicalStation station = new MeteorologicalStation();
		InfoBoard board = new InfoBoard();
		AverageBoard avgBoard = new AverageBoard();
		PreBoard preBoard = new PreBoard();
		station.addSubScriber(board);
		station.addSubScriber(avgBoard);
		station.addSubScriber(preBoard);
		station.start();
	}
}
