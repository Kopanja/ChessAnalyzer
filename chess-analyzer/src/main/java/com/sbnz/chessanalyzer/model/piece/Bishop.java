package com.sbnz.chessanalyzer.model.piece;


public class Bishop extends Piece{
	public Bishop(int color) {
		this.setValue(3);
		this.setColor(color);
	}
	
	public Bishop(int color, int counter) {
		super(color, counter);
		this.setValue(3);
	}

	@Override
	public String getFEN() {
		
		if(this.getColor() == 0)
			return "B";
		else
			return "b";
	}
	
	@Override
	public String toString() {
		return this.getFEN();
	}

}
