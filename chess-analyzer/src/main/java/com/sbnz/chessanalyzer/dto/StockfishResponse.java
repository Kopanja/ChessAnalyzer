package com.sbnz.chessanalyzer.dto;

import java.util.List;

public class StockfishResponse {

	private MoveDTO stockfishMove;
	private List<StockRecomendation> bestPlayerMoves;
	
	public MoveDTO getStockfishMove() {
		return stockfishMove;
	}
	public void setStockfishMove(MoveDTO stockfishMove) {
		this.stockfishMove = stockfishMove;
	}
	public List<StockRecomendation> getBestPlayerMoves() {
		return bestPlayerMoves;
	}
	public void setBestPlayerMoves(List<StockRecomendation> bestPlayerMoves) {
		this.bestPlayerMoves = bestPlayerMoves;
	}
	@Override
	public String toString() {
		String bestMovesString = "";
		for(StockRecomendation move : this.bestPlayerMoves) {
			bestMovesString += " " + move.toString();
		}
		return "StockfishResponse [stockfishMove=" + stockfishMove + ", bestPlayerMoves=" + bestMovesString + "]";
	}
	
	
	
}
