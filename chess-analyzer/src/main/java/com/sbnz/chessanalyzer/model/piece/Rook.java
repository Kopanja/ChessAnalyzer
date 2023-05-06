package com.sbnz.chessanalyzer.model.piece;

public class Rook extends Piece{

	public Rook(int color) {
		this.setValue(5);
		this.setColor(color);
	}
	
	public Rook(int color, int counter) {
		super(color, counter);
		this.setValue(5);
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

	@Override
	public String getPieceName() {
		return "Rook";
	}

}
