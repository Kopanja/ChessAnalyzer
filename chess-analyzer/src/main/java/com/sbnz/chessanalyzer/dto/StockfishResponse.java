package com.sbnz.chessanalyzer.dto;

import java.util.List;

public class StockfishResponse {

	private MoveDTO stockfishMove;
	private List<MoveDTO> bestPlayerMoves;
	
	public MoveDTO getStockfishMove() {
		return stockfishMove;
	}
	public void setStockfishMove(MoveDTO stockfishMove) {
		this.stockfishMove = stockfishMove;
	}
	public List<MoveDTO> getBestPlayerMoves() {
		return bestPlayerMoves;
	}
	public void setBestPlayerMoves(List<MoveDTO> bestPlayerMoves) {
		this.bestPlayerMoves = bestPlayerMoves;
	}
	@Override
	public String toString() {
		String bestMovesString = "";
		for(MoveDTO move : this.bestPlayerMoves) {
			bestMovesString += " " + move.toString();
		}
		return "StockfishResponse [stockfishMove=" + stockfishMove + ", bestPlayerMoves=" + bestMovesString + "]";
	}
	
	
	
}
