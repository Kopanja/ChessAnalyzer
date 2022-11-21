package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class IsAttacking {

	@Position(0)
	private Piece attackingPiece;
	
	@Position(1)
	private Piece attackedPiece;

	public IsAttacking() {
		super();
	}

	public IsAttacking(Piece attackingPiece, Piece attackedPiece) {
		super();
		this.attackingPiece = attackingPiece;
		this.attackedPiece = attackedPiece;
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
	
	
	
}
