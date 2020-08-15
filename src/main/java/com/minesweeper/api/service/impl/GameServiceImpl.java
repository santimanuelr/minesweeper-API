package com.minesweeper.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.repository.GameRepository;
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

}
