Feature: Game Minesweeper logic

	Scenario: User creates a new default game
	    When the user starts new default game
	    Then the mines board has 9 rows, 9 columns and 3 mines
	
	Scenario: User creates a new default game and mark a locker with a question
	    When the user starts new default game
	    And the user marks the locker 7 6 with a question mark
	    Then the board has a locker in row 7 and column 6 with a question mark