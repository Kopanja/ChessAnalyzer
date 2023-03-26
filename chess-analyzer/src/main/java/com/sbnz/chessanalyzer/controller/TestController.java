package com.sbnz.chessanalyzer.controller;



import java.util.List;

import com.sbnz.chessanalyzer.dto.FenDTO;
import com.sbnz.chessanalyzer.model.Move;
import com.sbnz.chessanalyzer.model.stock_drools.AnalysisObject;
import com.sbnz.chessanalyzer.service.KieSessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
