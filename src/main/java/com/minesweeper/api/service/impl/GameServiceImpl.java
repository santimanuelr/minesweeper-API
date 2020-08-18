package com.minesweeper.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.GameRequest;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.repository.GameRepository;
import com.minesweeper.api.rules.AddMines;
import com.minesweeper.api.rules.CheckBlankLocker;
import com.minesweeper.api.rules.CheckBombLocker;
import com.minesweeper.api.rules.MarkFlagLocker;
import com.minesweeper.api.rules.MarkQuestionLocker;
import com.minesweeper.api.rules.PlaceNumbers;
import com.minesweeper.api.rules.UncheckLocker;
import com.minesweeper.api.service.GameService;

@Service
public class GameServiceImpl implements GameService {

    private final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
    
    @Autowired
    private GameRepository gameRepository;

	@Override
	public Game save(GameRequest newGame) {
		Game game;
		if (newGame.getX() == null || newGame.getY() == null) {
			game = createNewDefaultGame();
		} else {
			game = createCustomGame(new Game(newGame.getY(), newGame.getX(), newGame.getMinesCount(), newGame.getName()));
		}
		return gameRepository.save(game);
	}

	private Game createCustomGame(Game game) {
		RulesEngine rulesEngine = new DefaultRulesEngine();
		Facts fact = new Facts();
		fact.put("game", game);
		rulesEngine.fire(getCreateGameRules(), fact);
		return game;
	}

	@Override
	public void deleteById(String id) {
		gameRepository.deleteById(id);		
	}

	@Override
	public List<Game> findAll() {
		return gameRepository.findAll();
	}

	@Override
	public Optional<Game> findById(String id) {
		return gameRepository.findById(id);
	}

	@Override
	public Game play(LockerRequest lr, Game game) throws Exception {
		log.info("Start play with request x: {} - y: {}", lr.getX(), lr.getY());
		
		if (game == null) {
			Optional<Game> gameOptional = gameRepository.findById(lr.getIdGame());
			if (gameOptional.isEmpty()) {
				throw new Exception();
			} else {
				game = gameOptional.get();
			}
		}
		
		RulesEngine rulesEngine = new DefaultRulesEngine();
		Facts fact = new Facts();
		fact.put("game", game);
		fact.put("request", lr);
		rulesEngine.fire(getPlayRules(), fact);
		gameRepository.save(game);
		return game;
	}

	private Rules getPlayRules() {
		Rules rules = new Rules();
		rules.register(new CheckBombLocker());
		rules.register(new CheckBlankLocker());
		rules.register(new UncheckLocker());
		rules.register(new MarkQuestionLocker());
		rules.register(new MarkFlagLocker());
		return rules;
	}

	
	private Rules getCreateGameRules() {
		Rules rules = new Rules();
		rules.register(new AddMines());
		rules.register(new PlaceNumbers());
		return rules;
	}

	@Override
	public Game createNewDefaultGame() {
		RulesEngine rulesEngine = new DefaultRulesEngine();
		Facts fact = new Facts();
		Game game = new Game();
		fact.put("game", game);
		rulesEngine.fire(getCreateGameRules(), fact);
		return game;
	}
}
