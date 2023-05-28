package com.sbnz.chessanalyzer.model.knight_game;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public class GameStartEvent {

	private int score;
	
	

	public GameStartEvent() {
		super();
	}



	public GameStartEvent(int score) {
		super();
		this.score = score;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
}
