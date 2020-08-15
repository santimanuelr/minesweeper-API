Feature: Game Minesweeper logic

	Scenario: user creates a new game
	    When the user starts new game of 9 rows to 9 columns and 3 mines
	    Then the mines board has 9 rows, 9 columns and 3 mines