package com.minesweeper.api.service;

import java.util.List;
import java.util.Optional;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.LockerRequest;

public interface GameService {

	Game save(Game newGame);
	
	Game createNewDefaultGame();

	void deleteById(String id);

	List<Game> findAll();

	Optional<Game> findById(String id);
	
	Game play(LockerRequest lr, Game game);

}
