package com.sbnz.chessanalyzer.service;


import java.util.List;

import com.sbnz.chessanalyzer.model.Board;
import com.sbnz.chessanalyzer.model.Message;
import com.sbnz.chessanalyzer.model.Move;
import com.sbnz.chessanalyzer.model.Square;
import com.sbnz.chessanalyzer.model.piece.Piece;
import com.sbnz.chessanalyzer.util.FENParser;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieSessionService {
	
	private final KieSession kSession;
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

	

}
