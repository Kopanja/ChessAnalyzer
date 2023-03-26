package com.sbnz.chessanalyzer.model.piece;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.chessanalyzer.model.Square;

public abstract class Piece {

	private int color;
	private int value;
	private int boardNum;
	private List<Square> canMoveOn;
	
	public Piece() {
		this.canMoveOn = new ArrayList<Square>();
	}
	public Piece(int color) {
		this.color = color;
	}
	
	
	public Piece(int color, int boardNum) {
		super();
		this.color = color;
		this.boardNum = boardNum;
	}
	
	
	public Piece(int color, int value, int boardNum, List<Square> canMoveOn) {
		super();
		this.color = color;
		this.value = value;
		this.boardNum = boardNum;
		this.canMoveOn = canMoveOn;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public List<Square> getCanMoveOn() {
		return canMoveOn;
	}
	public void setCanMoveOn(List<Square> canMoveOn) {
		this.canMoveOn = canMoveOn;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public abstract String getFEN();
	
	
}
