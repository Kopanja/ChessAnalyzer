package com.sbnz.chessanalyzer.dto;

public class StockRecomendation {

	private String fromSquare;
	private String toSquare;
	private int priority;
	
	
	public String getFromSquare() {
		return fromSquare;
	}
	public void setFromSquare(String fromSquare) {
		this.fromSquare = fromSquare;
	}
	public String getToSquare() {
		return toSquare;
	}
	public void setToSquare(String toSquare) {
		this.toSquare = toSquare;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "StockRecomendation [fromSquare=" + fromSquare + ", toSquare=" + toSquare + "]";
	}
}
