package com.sbnz.chessanalyzer.model.piece;

public class Pawn extends Piece {

	public Pawn(int color) {
		this.setValue(1);
		this.setColor(color);
	}

	public Pawn(int color, int counter) {
		super(color, counter);
		this.setValue(1);
	}
	@Override
	public String getFEN() {
		if(this.getColor() == 0)
			return "P";
		else
			return "p";
	}

	@Override
	public String toString() {
		return this.getFEN();
	}

	@Override
	public String getPieceName() {
		return "Pawn";
	}
	
	

}
