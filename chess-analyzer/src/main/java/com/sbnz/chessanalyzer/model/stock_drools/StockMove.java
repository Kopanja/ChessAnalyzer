package com.sbnz.chessanalyzer.model.stock_drools;

public class StockMove {

	char color;
	String fromSquare;
	String toSquare;
	char piece;
	String san;
	int boardNum;
	
	public StockMove() {
		super();
	}

	
	public StockMove(String fromSquare, String toSquare) {
		super();
		this.fromSquare = fromSquare;
		this.toSquare = toSquare;
	}


	public StockMove(char color, String fromSquare, String toSquare, char piece, String san) {
		super();
		this.color = color;
		this.fromSquare = fromSquare;
		this.toSquare = toSquare;
		this.piece = piece;
		this.san = san;
	}
	
	

	public StockMove(char color, String fromSquare, String toSquare, char piece, String san, int boardNum) {
		super();
		this.color = color;
		this.fromSquare = fromSquare;
		this.toSquare = toSquare;
		this.piece = piece;
		this.san = san;
		this.boardNum = boardNum;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public String getFromSquare() {
		return fromSquare;
	}

	public void setFromSquare(String fromSquare) {
		this.fromSquare = fromSquare;
	}

	public String getToSquare() {
		return toSquare;
	}

	public void setToSquare(String toSquare) {
		this.toSquare = toSquare;
	}

	public char getPiece() {
		return piece;
	}

	public void setPiece(char piece) {
		this.piece = piece;
	}

	public String getSan() {
		return san;
	}

	public void setSan(String san) {
		this.san = san;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	@Override
	public String toString() {
		return "StockMove [color=" + color + ", fromSquare=" + fromSquare + ", toSquare=" + toSquare + ", piece="
				+ piece + ", san=" + san + ", boardNum=" + boardNum + "]";
	}
	
	
	
	
}
