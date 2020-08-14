package com.minesweeper.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minesweeper.api.domain.Locker;

/**
 * Spring Data MongoDB repository for the Locker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LockerRepository extends MongoRepository<Locker, String> {
}
