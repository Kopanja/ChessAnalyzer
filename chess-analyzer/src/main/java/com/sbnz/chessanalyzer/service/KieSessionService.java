package com.sbnz.chessanalyzer.service;


import java.util.ArrayList;
import java.util.List;


import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.sbnz.chessanalyzer.dto.CanCaptureDTO;
import com.sbnz.chessanalyzer.dto.MoveDTO;
import com.sbnz.chessanalyzer.model.Board;
import com.sbnz.chessanalyzer.model.CanCapture;
import com.sbnz.chessanalyzer.model.Message;
import com.sbnz.chessanalyzer.model.Move;
import com.sbnz.chessanalyzer.model.Square;
import com.sbnz.chessanalyzer.model.stock_drools.AnalysisObject;
import com.sbnz.chessanalyzer.model.stock_drools.StockMove;
import com.sbnz.chessanalyzer.util.FENParser;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieSessionService {
	
	private KieSession kSession;
	private final KieContainer kieContainer;
	
	@Autowired
	public KieSessionService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		this.kSession =kieContainer.newKieSession("ksession-rules");
		//this.kSession = kieContainer.newKieSession();
			
		
	}
	
	public void test() {
		Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSession.insert(message);
        kSession.fireAllRules();
	}

	public void test2() {
		Message message = new Message();
        message.setMessage("Nova Poruka ");
        message.setStatus(6);
        kSession.insert(message);
        kSession.fireAllRules();
		
	}


	public void insertMoves(List<Move> moves) {
		for(Move move : moves) {
			kSession.insert(move);
		}
		
		kSession.fireAllRules();
		
	}
	
	
	public void insertBoard(String fen) {
		kSession.insert(FENParser.parse(fen, 0));
		kSession.fireAllRules();
	}
	
	public void insertOneMove(AnalysisObject obj) {
		System.out.println("USAO");
		kSession.insert(FENParser.parse(obj.getPreMoveFen(), 0));
		kSession.insert(FENParser.parse(obj.getPreMoveFen(), 1));
		kSession.fireAllRules();
		System.out.println("---------------------DOne------------------------------------------");
		kSession.insert(obj.getMove());
		kSession.fireAllRules();
		for(StockMove move : obj.getPostMoveLine()) {
			move.setBoardNum(0);
			kSession.insert(move);
			kSession.fireAllRules();
		}
	}
	
	public List<CanCaptureDTO> moveMade(StockMove move) {
		kSession.insert(move);
		kSession.fireAllRules();
		List<CanCaptureDTO> canCaptures = new ArrayList<CanCaptureDTO>();
		QueryResults results = kSession.getQueryResults("findCanCapture");
		for(QueryResultsRow queryResult : results) {
			CanCapture cc = (CanCapture) queryResult.get("$cc");
			System.out.println(cc);
			Square fromSquare = (Square) queryResult.get("$fromSquare");
			//System.out.println("Uzeo From Square");
			Square toSquare = (Square) queryResult.get("$toSquare");
			String message = (String) queryResult.get("$message");
			//System.out.println("From Square " + fromSquare.getSquareText());
			//System.out.println("To Square " + toSquare.getSquareText());
			//System.out.println(message);
			canCaptures.add(new CanCaptureDTO(fromSquare.getSquareText(), toSquare.getSquareText(), message));
			
		}
		return canCaptures;
	}
	
	public void initilizeBoard() {
		//Board startBoard = FENParser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 ", 0);
		kSession.destroy();
		kSession = kieContainer.newKieSession("ksession-rules");

		Board startBoard = FENParser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 ", 0);
		Board simBoard1 = FENParser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 ", 1);
		//int firedRules = kSession.fireAllRules();
		//System.out.println(firedRules);
		kSession.insert(startBoard);
		kSession.insert(simBoard1);
		//System.out.println(this.startBoard);
		kSession.fireAllRules();
		System.out.println(kSession.getFactCount());
		//System.out.println(this.startBoard);
	}


}
