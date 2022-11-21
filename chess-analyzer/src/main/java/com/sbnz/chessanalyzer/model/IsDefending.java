package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class IsDefending {

	@Position(0)
	private Piece defendingPiece;
	
	@Position(1)
	private Piece defendedPiece;

	public IsDefending() {
		super();
	}

	public IsDefending(Piece defendingPiece, Piece defendedPiece) {
		super();
		this.defendingPiece = defendingPiece;
		this.defendedPiece = defendedPiece;
	}

	public Piece getDefendingPiece() {
		return defendingPiece;
	}

	public void setDefendingPiece(Piece defendingPiece) {
		this.defendingPiece = defendingPiece;
	}

	public Piece getDefendedPiece() {
		return defendedPiece;
	}

	public void setDefendedPiece(Piece defendedPiece) {
		this.defendedPiece = defendedPiece;
	}
	
	
	
}
