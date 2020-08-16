package com.minesweeper.api.bdd.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.service.GameService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GameStepDefinitions {
	
	private final Logger log = LoggerFactory.getLogger(GameStepDefinitions.class);
	
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
	
	@And("^the user marks the locker (\\d+) (\\d+) with a (.+) mark$")
	public void theUserMarksALocker(int row, int column, String markType) throws Throwable {
		LockerRequest lockerRequest = new LockerRequest(row, column);
		switch (markType) {
			case "question":
				lockerRequest.setQuestion(Boolean.TRUE);
				break;
			case "flag":
				lockerRequest.setFlag(Boolean.TRUE);
				break;
			default:
				break;
		}
		gameService.play(lockerRequest, this.game);
    }
	
	@Then("^the board has a locker in row (\\d+) and column (\\d+) with (a|no a) question mark$")
	public void theBoardHasALockerWithQuestionMark(int row, int column, String not) throws Throwable {
		if (not.trim().equals("a")) {
			assertFalse(this.game.getLockers().get(row).get(column).isExposed());
			assertFalse(this.game.getLockers().get(row).get(column).isFlag());
			assertTrue(this.game.getLockers().get(row).get(column).isQuestion());
		} else {
			assertFalse(this.game.getLockers().get(row).get(column).isQuestion());
		}
    }
	
	@Then("^the board has a locker in row (\\d+) and column (\\d+) with (a|no a) flag mark$")
	public void theBoardHasALockerWithFlagMark(int row, int column, String not) throws Throwable {
		if (not.trim().equals("a")) {
			assertFalse(this.game.getLockers().get(row).get(column).isExposed());
			assertFalse(this.game.getLockers().get(row).get(column).isQuestion());
			assertTrue(this.game.getLockers().get(row).get(column).isFlag());
		} else {
			assertFalse(this.game.getLockers().get(row).get(column).isFlag());
		}
    }
	
}
