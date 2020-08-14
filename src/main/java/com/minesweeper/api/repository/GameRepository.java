package com.minesweeper.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minesweeper.api.domain.Game;

/**
 * Spring Data MongoDB repository for the Game entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
