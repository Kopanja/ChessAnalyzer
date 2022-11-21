package com.sbnz.chessanalyzer.dto;

public class FenDTO {

	private String fen;
	
	

	public FenDTO() {
		super();
	}

	public FenDTO(String fen) {
		super();
		this.fen = fen;
	}

	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}
	
	
}
