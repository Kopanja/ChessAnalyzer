package com.sbnz.chessanalyzer.dto;

public class CanCaptureDTO {

	private String fromSquare;
	private String toSquare;
	private String message;
	
	
	public CanCaptureDTO(String fromSquare, String toSquare, String message) {
		super();
		this.fromSquare = fromSquare;
		this.toSquare = toSquare;
		this.message = message;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
