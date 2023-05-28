package com.sbnz.chessanalyzer.model.knight_game;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public class RoundStartEvent {

	private String squareText;
	private boolean isActiveRound;

	
	public RoundStartEvent() {
		super();
		this.isActiveRound = true;
		
	}

	public RoundStartEvent(String squareText) {
		super();
		this.squareText = squareText;
		this.isActiveRound = true;
	}

	public String getSquareText() {
		return squareText;
	}

	public void setSquareText(String squareText) {
		this.squareText = squareText;
	}

	public boolean getIsActiveRound() {
		return isActiveRound;
	}

	public void setIsActiveRound(boolean isActiveRound) {
		this.isActiveRound = isActiveRound;
	}

	
}
