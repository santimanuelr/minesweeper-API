package com.minesweeper.api.bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.minesweeper.api.ApiApplicationTests;
import com.minesweeper.api.domain.Game;
import com.minesweeper.api.service.GameService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GameStepDefinitions {
	
	@Autowired private GameService gameService;
	
	private Game game;
	
	@When("^the user starts new game of (\\d+) rows to (\\d+) columns and (\\d+) mines$")
	public void theUserStartsNewGame(int rows, int columns, int mines) throws Throwable {
        this.game = gameService.createNewDefaultGame();
    }
	
	@When("^the user starts new default game$")
	public void theUserStartsNewDefaultGame() throws Throwable {
        this.game = gameService.createNewDefaultGame();
    }
	
	@Then("^the mines board has (\\d+) rows, (\\d+) columns and (\\d+) mines$")
	public void theBoardHasParameterOf(int rows, int columns, int mines) throws Throwable {
		assertEquals(rows, this.game.getLockers().size(), "");
		assertEquals(columns, this.game.getLockers().get(rows-1).size(), "");
		assertEquals(mines, this.game.getMinesCount(), "");
    }
	
}
