package com.sbnz.chessanalyzer.dto;

import java.util.List;

public class AnalysisObjectDTO {
	
	private MoveDTO stockfishMove;
	private List<CanCaptureDTO> canCaptures;
	private List<StockRecomendation> bestPlayerMoves;
	
	public AnalysisObjectDTO(MoveDTO stockfishMove, List<CanCaptureDTO> canCaptures) {
		super();
		this.stockfishMove = stockfishMove;
		this.canCaptures = canCaptures;
	}
	
	
	
	public AnalysisObjectDTO(MoveDTO stockfishMove, List<CanCaptureDTO> canCaptures, List<StockRecomendation> bestPlayerMoves) {
		super();
		this.stockfishMove = stockfishMove;
		this.canCaptures = canCaptures;
		this.bestPlayerMoves = bestPlayerMoves;
	}



	public AnalysisObjectDTO() {
		super();
	}


	public MoveDTO getStockfishMove() {
		return stockfishMove;
	}
	public void setStockfishMove(MoveDTO stockfishMove) {
		this.stockfishMove = stockfishMove;
	}
	public List<CanCaptureDTO> getCanCaptures() {
		return canCaptures;
	}
	public void setCanCaptures(List<CanCaptureDTO> canCaptures) {
		this.canCaptures = canCaptures;
	}



	public List<StockRecomendation> getBestPlayerMoves() {
		return bestPlayerMoves;
	}



	public void setBestPlayerMoves(List<StockRecomendation> bestPlayerMoves) {
		this.bestPlayerMoves = bestPlayerMoves;
	}
	
	
	

}
