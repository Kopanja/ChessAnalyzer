package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Bishop;
import com.sbnz.chessanalyzer.model.piece.King;
import com.sbnz.chessanalyzer.model.piece.Knight;
import com.sbnz.chessanalyzer.model.piece.Pawn;
import com.sbnz.chessanalyzer.model.piece.Piece;
import com.sbnz.chessanalyzer.model.piece.Queen;
import com.sbnz.chessanalyzer.model.piece.Rook;

import org.kie.api.definition.type.Position;

public class Square {

	
	private int boardNum;
	
	private int rank;
	
	private int file;
	
	private String squareText;
	
	@Position(0)
	private Piece piece;
	
	
	public Square() {
		
	}
	
	public Square(int row, int column) {
		super();
		char[] columnName = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

		this.rank = row;
		this.file = column;
		this.squareText = columnName[column] + String.valueOf(this.rank+1);
		
	}
	
	public Square(int row, int column, int boardNum) {
		super();
		char[] columnName = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		this.boardNum = boardNum;
		this.rank = row;
		this.file = column;
		this.squareText = columnName[column] + String.valueOf(this.rank+1);
		
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int row) {
		this.rank = row;
	}

	public int getFile() {
		return file;
	}

	
	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public void setFile(int column) {
		this.file = column;
	}

	public String getSquareText() {
		return squareText;
	}

	public void setSquareText(String squareText) {
		this.squareText = squareText;
	}
	
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void createPieceFromFen(String fen, int counter) {
		switch(fen) {
		  case "P":
		    this.piece = new Pawn(0, counter);
		    break;
		  case "p":
			  this.piece = new Pawn(1, counter);
		    break;
		  case "N":
			  this.piece = new Knight(0, counter);
			break;
		  case "n":
			  this.piece = new Knight(1, counter);
			break;
		  case "B":
			  this.piece = new Bishop(0, counter);
			break;
		  case "b":
			  this.piece = new Bishop(1, counter);
			break;
		  case "R":
			  this.piece = new Rook(0, counter);
			break;
		  case "r":
			  this.piece = new Rook(1, counter);
			break;
		  case "Q":
			  this.piece = new Queen(0, counter);
			break;
		  case "q":
			  this.piece = new Queen(1, counter);
			break;
		  case "K":
			  this.piece = new King(0, counter);
		    break;
		  case "k":
			  this.piece = new King(1, counter);
		    break;
		  default:
		    this.piece = null;
		}
	}
	@Override
	public String toString() {
		if(this.piece != null)
			return piece.getFEN();
		else
			return "#";
	}
	
	
	
}
