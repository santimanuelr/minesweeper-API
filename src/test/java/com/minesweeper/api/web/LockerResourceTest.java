package com.minesweeper.api.web;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.minesweeper.api.ApiApplication;
import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.service.GameService;

@SpringBootTest(classes = ApiApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LockerResourceTest {
	
	public static final String HOST = "http://localhost:";
    public static final String ENDPOINT_LOCKERS = "/api/lockers";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private GameService gameService;
    
    @Test
    public void putNotValidLockerRequest() throws Exception {

    	LockerRequest locker = new LockerRequest();       
    	HttpEntity<LockerRequest> request = new HttpEntity<LockerRequest>(locker);
    	ResponseEntity<Game> gameResponse = restTemplate.exchange(new URL(HOST + port + ENDPOINT_LOCKERS).toString(), 
    			HttpMethod.PUT, request, Game.class);
    	
    	assertEquals(gameResponse.getStatusCode(), HttpStatus.BAD_REQUEST);

    }
    
    @Test
    public void putNotValidLockerRequest2() throws Exception {

    	LockerRequest locker = new LockerRequest();
    	locker.setExposed(Boolean.TRUE);
    	locker.setFlag(Boolean.TRUE);
    	HttpEntity<LockerRequest> request = new HttpEntity<LockerRequest>(locker);
    	ResponseEntity<Game> gameResponse = restTemplate.exchange(new URL(HOST + port + ENDPOINT_LOCKERS).toString(), 
    			HttpMethod.PUT, request, Game.class);
    	
    	assertEquals(gameResponse.getStatusCode(), HttpStatus.BAD_REQUEST);

    }
    
    @Test
    public void putNotValidLockerRequest3() throws Exception {
    	
    	gameService.createNewDefaultGame();
    	List<Game> games = gameService.findAll();
    	assertFalse(games.isEmpty());

    	LockerRequest locker = new LockerRequest();
    	locker.setIdGame(games.get(0).getId());
    	locker.setX(8);
    	locker.setY(8);
    	locker.setExposed(Boolean.TRUE);
    	HttpEntity<LockerRequest> request = new HttpEntity<LockerRequest>(locker);
    	
    	ResponseEntity<Game> gameRequest = restTemplate.exchange(new URL(HOST + port + ENDPOINT_LOCKERS).toString(), 
    			HttpMethod.PUT, request, Game.class);
    	
    	assertEquals(gameRequest.getStatusCode(), HttpStatus.OK);

    }

}
