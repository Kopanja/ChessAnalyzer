package com.sbnz.chessanalyzer.util;

import com.sbnz.chessanalyzer.model.Board;

public class FENParser {
	
	private Board board;
	
	public FENParser() {
		board = new Board();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public static Board parse(String fen, int counter) {
		Board board = new Board(counter);
		String filledFen = fillEmptySquaresInFen(fen);
		String[] ranks = filledFen.split("/");
		
		for(int rank = 0; rank < ranks.length; rank++) {
			String[] files = ranks[rank].split("");
			for(int file = 0; file<files.length; file++) {
				//System.out.println(this.board.getSquares()[rank][file]);
				board.getSquares()[7-rank][file].createPieceFromFen(files[file], counter);
			}
			
		}
		board.setFen(fen);
		return board;
	}
	
	private static String fillEmptySquaresInFen(String fen) {
		String retString = "";
		String[] list1 = fen.split(" ");
		String[] ranks = list1[0].split("/");
		for(int rank = 0; rank < ranks.length; rank++) {
					
					String[] files = ranks[rank].split("");
					
					for(int file = 0; file < files.length; file++) {
						if(isNumeric(files[file])) {
							int num = Integer.parseInt(files[file]);
							for(int i = 0; i < num; i++) {
								retString += "#";
							}
						}else {
							retString += files[file];
						}
					}
					retString += "/";
				}
		retString = retString.substring(0,retString.length() - 1);
		return retString;
	}
	
	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
	        return false;
	    }
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}
