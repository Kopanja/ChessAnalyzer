package com.sbnz.chessanalyzer.model.knight_game.dto;

import com.sbnz.chessanalyzer.model.knight_game.EventType;

public class EventDTO {

	private EventType type;
	private String square;
	private boolean isCorrect;
	private int scorePoints;
	
	public EventDTO(EventType type, String square) {
		super();
		this.type = type;
		this.square = square;
	}
	
	
	public EventDTO(EventType type, String square, int scorePoints) {
		super();
		this.type = type;
		this.square = square;
		this.scorePoints = scorePoints;
	}


	public EventDTO() {
		super();
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
	public int getScorePoints() {
		return scorePoints;
	}


	public void setScorePoints(int scorePoints) {
		this.scorePoints = scorePoints;
	}


	@Override
	public String toString() {
		return "EventDTO [type=" + type + ", square=" + square + "]";
	}
	
	
	
	
	
}
