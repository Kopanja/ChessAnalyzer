package com.sbnz.chessanalyzer.model.piece;

public class Knight extends Piece{

	
	public Knight(int color) {
		this.setValue(3);
		this.setColor(color);
	}
	
	public Knight(int color, int counter) {
		super(color, counter);
		this.setValue(3);
	}

	@Override
	public String getFEN() {
		if(this.getColor() == 0)
			return "N";
		else
			return "n";
	}
	
	
	@Override
	public String toString() {
		return this.getFEN();
	}

}
