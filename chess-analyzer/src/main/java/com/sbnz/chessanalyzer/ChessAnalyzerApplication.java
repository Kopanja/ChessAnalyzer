package com.sbnz.chessanalyzer;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChessAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessAnalyzerApplication.class, args);
	}
	
	@Bean
	public KieContainer kieSession() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.sbnz", "chess-drools", "1.0.0-SNAPSHOT"));
		
       
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

}
