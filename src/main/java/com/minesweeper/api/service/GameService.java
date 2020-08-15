package com.minesweeper.api.service;

import java.util.List;
import java.util.Optional;

import com.minesweeper.api.domain.Game;

public interface GameService {

	Game save(Game newGame);

	void deleteById(String id);

	List<Game> findAll();

	Optional<Game> findById(String id);

    //private final Logger log = LoggerFactory.getLogger(GameService.class);

}
