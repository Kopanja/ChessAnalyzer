package com.sbnz.chessanalyzer.model.piece;

public class Rook extends Piece{

	public Rook(int color) {
		this.setColor(color);
	}
	
	public Rook(int color, int counter) {
		super(color, counter);
	}
	@Override
	public String getFEN() {
		if(this.getColor() == 0)
			return "R";
		else
			return "r";
	}
	
	
	@Override
	public String toString() {
		return this.getFEN();
	}

}
