package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class IsOnPath {

	@Position(0)
	private Piece piece;
	
	@Position(1)
	private Square square;
	
	

	public IsOnPath() {
		super();
	}



	public IsOnPath(Piece piece, Square square) {
		super();
		this.piece = piece;
		this.square = square;
	}



	public Piece getPiece() {
		return piece;
	}



	public void setPiece(Piece piece) {
		this.piece = piece;
	}



	public Square getSquare() {
		return square;
	}



	public void setSquare(Square square) {
		this.square = square;
	}



	@Override
	public String toString() {
		return "IsOnPath [piece=" + piece + ", square=" + square + "]";
	}
	
	
	
}
