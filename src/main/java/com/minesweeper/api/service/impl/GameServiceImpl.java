package com.minesweeper.api.service.impl;

import java.awt.Point;
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
import com.minesweeper.api.repository.GameRepository;
import com.minesweeper.api.rules.CheckBlankLocker;
import com.minesweeper.api.service.GameService;

@Service
public class GameServiceImpl implements GameService {

    private final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
    
    @Autowired
    private GameRepository gameRepository;

	@Override
	public Game save(Game newGame) {
		return gameRepository.save(newGame);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Game> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game play(Point point, Game game) {
		// TODO Auto-generated method stub
		RulesEngine rulesEngine = new DefaultRulesEngine();
		Facts fact = new Facts();
		fact.put("game", game);
		fact.put("point", point);
		rulesEngine.fire(getPlayRules(), fact);
		return null;
	}

	private Rules getPlayRules() {
		Rules rules = new Rules();
		rules.register(new CheckBlankLocker());
		return rules;
	}

}
