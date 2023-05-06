package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class CanCapture {

	@Position(0)
	private Piece attackingPiece;
	
	@Position(1)
	private Piece attackedPiece;
	
	@Position(2)
	private String message;

	public CanCapture(Piece attackingPiece, Piece attackedPiece) {
		super();
		this.attackingPiece = attackingPiece;
		this.attackedPiece = attackedPiece;
	}

	
	
	public CanCapture(Piece attackingPiece, Piece attackedPiece, String message) {
		super();
		this.attackingPiece = attackingPiece;
		this.attackedPiece = attackedPiece;
		this.message = message;
	}



	public Piece getAttackingPiece() {
		return attackingPiece;
	}

	public void setAttackingPiece(Piece attackingPiece) {
		this.attackingPiece = attackingPiece;
	}

	public Piece getAttackedPiece() {
		return attackedPiece;
	}

	public void setAttackedPiece(Piece attackedPiece) {
		this.attackedPiece = attackedPiece;
	}

	
	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	@Override
	public String toString() {
		return "CanCapture [attackingPiece=" + attackingPiece + ", attackedPiece=" + attackedPiece + "]";
	}
	
	
	
}
