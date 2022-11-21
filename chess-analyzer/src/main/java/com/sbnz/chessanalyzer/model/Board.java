package com.sbnz.chessanalyzer.model;

public class Board {

	private int boardNum;
	private Square[][] squares;
	private String fen;
	
	
	
	
	public Board(int boardNum) {
		super();
		this.boardNum = boardNum;
		this.squares = new Square[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j<8; j++){
				this.squares[i][j] = new Square(i,j, boardNum);
			}
		}
	}


	public Board() {
		this.squares = new Square[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j<8; j++){
				this.squares[i][j] = new Square(i,j);
			}
		}
	}


	public Square[][] getSquares() {
		return squares;
	}


	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
	
	
	
	public int getBoardNum() {
		return boardNum;
	}


	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	

	public String getFen() {
		return fen;
	}


	public void setFen(String fen) {
		this.fen = fen;
	}


	@Override
	public String toString() {
		String board = "Board Num: " + boardNum + "\n";
		for(int i = 7; i >=0; i--) {
			for(int j = 0; j<8; j++){
				board += this.squares[i][j].toString() + "|";
			}
			board += "\n";
		}
		return board;
	}
}
