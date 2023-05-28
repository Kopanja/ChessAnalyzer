package com.sbnz.chessanalyzer.service;

import com.sbnz.chessanalyzer.model.knight_game.dto.EventDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

	@Autowired
	SimpMessagingTemplate template;
	
	public void sendEvent(EventDTO event) {
		template.convertAndSend("/topic/message", event);
	}
}
