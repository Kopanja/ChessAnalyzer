package com.sbnz.chessanalyzer.model.piece;

public class King extends Piece{

	
	public King(int color) {
		this.setValue(0);
		this.setColor(color);
	}
	
	public King(int color, int counter) {
		super(color, counter);
		this.setValue(0);
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

	@Override
	public String getPieceName() {
		return "King";
	}

}
