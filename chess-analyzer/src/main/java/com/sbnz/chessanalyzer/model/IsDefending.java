package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class IsDefending {

	@Position(0)
	private Piece defendingPiece;
	
	@Position(1)
	private Piece defendedPiece;

	@Position(2)
	private int boardNum;
	
	
	public IsDefending() {
		super();
	}

	public IsDefending(Piece defendingPiece, Piece defendedPiece) {
		super();
		this.defendingPiece = defendingPiece;
		this.defendedPiece = defendedPiece;
	}

	
	public IsDefending(Piece defendingPiece, Piece defendedPiece, int boardNum) {
		super();
		this.defendingPiece = defendingPiece;
		this.defendedPiece = defendedPiece;
		this.boardNum = boardNum;
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

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	
	
}
