package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;


public class CanMoveOn {

	@Position(0)
	private Piece piece;
	
	@Position(1)
	private Square square;

	@Position(2)
	private CanMoveOnType type;
	
	public CanMoveOn() {
		super();
	}



	public CanMoveOn(Piece piece, Square square) {
		super();
		this.piece = piece;
		this.square = square;
	}



	public CanMoveOn(Piece piece, Square square, CanMoveOnType type) {
		super();
		this.piece = piece;
		this.square = square;
		this.type = type;
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

	


	public CanMoveOnType getType() {
		return type;
	}



	public void setType(CanMoveOnType type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "CanMoveOn [piece=" + piece + ", square=" + square + "]";
	}
	
	public enum CanMoveOnType {
        RIGHT_UP, LEFT_UP, RIGHT_DOWN, LEFT_DOWN, KNIGHT;
    }

	
	
}
