package com.sbnz.chessanalyzer.model.knight_game;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public class SquareSelectedEvent {

	private String square;

	public SquareSelectedEvent(String square) {
		super();
		this.square = square;
	}

	public SquareSelectedEvent() {
		super();
	}

	public String getSquare() {
		return square;
	}

	public void setSquare(String square) {
		this.square = square;
	}
	
	
}
