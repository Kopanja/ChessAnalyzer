package com.sbnz.chessanalyzer.model;

public class Move {
	public static final int BEST_MOVE = 1;
    public static final int GOOD_MOVE = 2;
    public static final int MISTAKE = 3;
    public static final int BLUNDER = 4;
    
    private String color;
    private String fromSquare;
    private String piece;
    private String san;
    private String bestMove;
    private String fen;
    private int preMoveEval;
    private int postMoveEval;
    private int bestMoveEval;
    private int label;
    private int moveNum;
    
	public Move() {
		super();
	}

	public Move(String color, String fromSquare, String piece, String san, String bestMove, String fen, int preMoveEval,
			int postMoveEval, int bestMoveEval, int label, int moveNum) {
		super();
		this.color = color;
		this.fromSquare = fromSquare;
		this.piece = piece;
		this.san = san;
		this.bestMove = bestMove;
		this.fen = fen;
		this.preMoveEval = preMoveEval;
		this.postMoveEval = postMoveEval;
		this.bestMoveEval = bestMoveEval;
		this.label = label;
		this.moveNum = moveNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFromSquare() {
		return fromSquare;
	}

	public void setFromSquare(String fromSquare) {
		this.fromSquare = fromSquare;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getSan() {
		return san;
	}

	public void setSan(String san) {
		this.san = san;
	}

	public String getBestMove() {
		return bestMove;
	}

	public void setBestMove(String bestMove) {
		this.bestMove = bestMove;
	}

	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}

	public int getPreMoveEval() {
		return preMoveEval;
	}

	public void setPreMoveEval(int preMoveEval) {
		this.preMoveEval = preMoveEval;
	}

	public int getPostMoveEval() {
		return postMoveEval;
	}

	public void setPostMoveEval(int postMoveEval) {
		this.postMoveEval = postMoveEval;
	}

	public int getBestMoveEval() {
		return bestMoveEval;
	}

	public void setBestMoveEval(int bestMoveEval) {
		this.bestMoveEval = bestMoveEval;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}

	@Override
	public String toString() {
		return "Move [color=" + color + ", fromSquare=" + fromSquare + ", piece=" + piece + ", san=" + san
				+ ", bestMove=" + bestMove + ", fen=" + fen + ", preMoveEval=" + preMoveEval + ", postMoveEval="
				+ postMoveEval + ", bestMoveEval=" + bestMoveEval + ", label=" + label + ", moveNum=" + moveNum + "]";
	}


	
	
    
	
}
