package com.sbnz.chessanalyzer.controller;



import java.util.List;

import com.sbnz.chessanalyzer.dto.AnalysisObjectDTO;
import com.sbnz.chessanalyzer.dto.CanCaptureDTO;
import com.sbnz.chessanalyzer.dto.FenDTO;
import com.sbnz.chessanalyzer.dto.FlaskSpringDTO;
import com.sbnz.chessanalyzer.dto.MoveDTO;
import com.sbnz.chessanalyzer.dto.SquareSelectedDTO;
import com.sbnz.chessanalyzer.dto.StockfishResponse;
import com.sbnz.chessanalyzer.model.Message;
import com.sbnz.chessanalyzer.model.Move;
import com.sbnz.chessanalyzer.model.stock_drools.AnalysisObject;
import com.sbnz.chessanalyzer.model.stock_drools.StockMove;
import com.sbnz.chessanalyzer.service.KieSessionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api/test")
public class TestController {
	
	@Autowired
	KieSessionService kieSessionService;
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> getAllWeapons() {
		kieSessionService.test();
	
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ResponseEntity<?> getAllWeapons2() {
		kieSessionService.test2();
	
		return new ResponseEntity<>("OK2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post-moves", method = RequestMethod.POST)
	public ResponseEntity<?> postMoves(@RequestBody List<Move> moves) {
		System.out.println(moves);
		kieSessionService.insertMoves(moves);
	
		return new ResponseEntity<>("OK2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post-fen", method = RequestMethod.POST)
	public ResponseEntity<?> postFen(@RequestBody FenDTO fen) {
		System.out.println(fen.getFen());
		kieSessionService.insertBoard(fen.getFen());
	
		return new ResponseEntity<>("OK2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post-one-move", method = RequestMethod.POST)
	public ResponseEntity<?> postOneMove(@RequestBody AnalysisObject obj) {
		kieSessionService.insertOneMove(obj);
	
		return new ResponseEntity<>("OK2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/init-board", method = RequestMethod.GET)
	public ResponseEntity<?> initBoard() {
		kieSessionService.initilizeBoard();
	
		return new ResponseEntity<>("OK2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post-one-move-2", method = RequestMethod.POST)
	public ResponseEntity<?> postOneMove2(@RequestBody MoveDTO move) {
		kieSessionService.moveMade(new StockMove(move.getFromSquare(), move.getToSquare()));
		MoveDTO moveForStockfish = new MoveDTO(move.getFromSquare(), move.getToSquare());
		
		final String uri = "http://localhost:5000/move-made";

	    RestTemplate restTemplate = new RestTemplate();
	    StockfishResponse stockfishResponse = restTemplate.postForObject(uri, moveForStockfish, StockfishResponse.class);
	    System.out.println(stockfishResponse);
	    List<CanCaptureDTO> canCaptures = kieSessionService.moveMade(new StockMove(stockfishResponse.getStockfishMove().getFromSquare(), stockfishResponse.getStockfishMove().getToSquare()));
	    AnalysisObjectDTO result = new AnalysisObjectDTO(stockfishResponse.getStockfishMove(), canCaptures, stockfishResponse.getBestPlayerMoves());
	  
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/start-knight-game", method = RequestMethod.GET)
	public ResponseEntity<?> knightGameStart() {
		String squareText = this.kieSessionService.startKnightGame();
		//String squareText = this.kieSessionService.templateTest();
		return new ResponseEntity<>(new SquareSelectedDTO(squareText), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/end-round", method = RequestMethod.GET)
	public ResponseEntity<?> knightGameEndRound() {
		this.kieSessionService.endRound();
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/square-selected", method = RequestMethod.POST)
	public ResponseEntity<?> squareSelected(@RequestBody SquareSelectedDTO squareSelectedDTO) {
		this.kieSessionService.squareClicked(squareSelectedDTO.getSquare());
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	


}
