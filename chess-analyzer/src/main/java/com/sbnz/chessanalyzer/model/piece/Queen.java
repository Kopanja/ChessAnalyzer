package com.sbnz.chessanalyzer.model.piece;

public class Queen extends Piece{

	public Queen(int color) {
		this.setValue(9);
		this.setColor(color);
	}
	
	public Queen(int color, int counter) {
		super(color, counter);
		this.setValue(9);
	}
	@Override
	public String getFEN() {
		if(this.getColor() == 0)
			return "Q";
		else
			return "q";
	}

	
	@Override
	public String toString() {
		return this.getFEN();
	}
}
