package com.sbnz.chessanalyzer.dto;

public class MoveDTO {
	private String fromSquare;
	private String toSquare;
	
	
	public MoveDTO() {
		super();
	}
	public MoveDTO(String fromSquare, String toSquare) {
		super();
		this.fromSquare = fromSquare;
		this.toSquare = toSquare;
	}
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
	@Override
	public String toString() {
		return "MoveDTO [fromSquare=" + fromSquare + ", toSquare=" + toSquare + "]";
	}
	
	

}
