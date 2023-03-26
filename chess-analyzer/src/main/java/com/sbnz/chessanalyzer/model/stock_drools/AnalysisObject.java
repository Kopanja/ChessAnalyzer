package com.sbnz.chessanalyzer.model.stock_drools;

import java.util.List;

public class AnalysisObject {
	String preMoveFen;
	StockMove move;
	List<StockMove> movesAfterBlunder;
	List<StockMove> preMoveLine;
	List<StockMove> postMoveLine;

	public AnalysisObject() {
		super();
	}

	public AnalysisObject(String preMoveFen, StockMove move, List<StockMove> movesAfterBlunder,
			List<StockMove> preMoveLine, List<StockMove> postMoveLine) {
		super();
		this.preMoveFen = preMoveFen;
		this.move = move;
		this.movesAfterBlunder = movesAfterBlunder;
		this.preMoveLine = preMoveLine;
		this.postMoveLine = postMoveLine;
	}

	public String getPreMoveFen() {
		return preMoveFen;
	}

	public void setPreMoveFen(String preMoveFen) {
		this.preMoveFen = preMoveFen;
	}

	public StockMove getMove() {
		return move;
	}

	public void setMove(StockMove move) {
		this.move = move;
	}

	public List<StockMove> getMovesAfterBlunder() {
		return movesAfterBlunder;
	}

	public void setMovesAfterBlunder(List<StockMove> movesAfterBlunder) {
		this.movesAfterBlunder = movesAfterBlunder;
	}

	public List<StockMove> getPreMoveLine() {
		return preMoveLine;
	}

	public void setPreMoveLine(List<StockMove> preMoveLine) {
		this.preMoveLine = preMoveLine;
	}

	public List<StockMove> getPostMoveLine() {
		return postMoveLine;
	}

	public void setPostMoveLine(List<StockMove> postMoveLine) {
		this.postMoveLine = postMoveLine;
	}

}
