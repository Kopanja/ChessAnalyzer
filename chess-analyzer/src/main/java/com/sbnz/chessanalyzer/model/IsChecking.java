package com.sbnz.chessanalyzer.model;

import com.sbnz.chessanalyzer.model.piece.King;
import com.sbnz.chessanalyzer.model.piece.Piece;

import org.kie.api.definition.type.Position;

public class IsChecking {

	@Position(0)
	private Piece attackingPiece;
	
	@Position(1)
	private King king;
	
	@Position(2)
	private int boardNum;
	

	public IsChecking() {
		super();
	}



	public IsChecking(Piece attackingPiece, King king) {
		super();
		this.attackingPiece = attackingPiece;
		this.king = king;
	}


	

	public IsChecking(Piece attackingPiece, King king, int boardNum) {
		super();
		this.attackingPiece = attackingPiece;
		this.king = king;
		this.boardNum = boardNum;
	}



	public Piece getAttackingPiece() {
		return attackingPiece;
	}



	public void setAttackingPiece(Piece attackingPiece) {
		this.attackingPiece = attackingPiece;
	}



	public King getKing() {
		return king;
	}



	public void setKing(King king) {
		this.king = king;
	}



	public int getBoardNum() {
		return boardNum;
	}



	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	
	
}
