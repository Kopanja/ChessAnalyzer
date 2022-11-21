package com.sbnz.chessanalyzer.model.piece;

public class King extends Piece{

	
	public King(int color) {
		this.setColor(color);
	}
	
	public King(int color, int counter) {
		super(color, counter);
	}
	@Override
	public String getFEN() {
		if(this.getColor() == 0)
			return "K";
		else
			return "k";
	}
	
	
	@Override
	public String toString() {
		return this.getFEN();
	}

}
