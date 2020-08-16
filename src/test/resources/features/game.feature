Feature: Game Minesweeper logic

	Scenario: User creates a new default game
	    When the user starts new default game
	    Then the mines board has 9 rows, 9 columns and 3 mines
	
	Scenario: User creates a new default game and mark a locker with a question
	    When the user starts new default game
	    And the user marks the locker 7 6 with a question mark
	    Then the board has a locker in row 7 and column 6 with a question mark
	    And the board has a locker in row 7 and column 6 with no a flag mark
	    
    Scenario: User creates a new default game and mark a locker with a flag
	    When the user starts new default game
	    And the user marks the locker 7 6 with a flag mark
	    Then the board has a locker in row 7 and column 6 with a flag mark
	    And the board has a locker in row 7 and column 6 with no a question mark
	    
	Scenario: User creates a new default game and exposes a locker with a bomb - Game Over
	    When the user starts new default game
	    And the user exposes a locker with a bomb
	    Then the game is over
	    
	Scenario: User creates a new default game and exposes a blank locker - Game continue
	    When the user starts new default game
	    And the user exposes a blank locker 
	    Then the game continues