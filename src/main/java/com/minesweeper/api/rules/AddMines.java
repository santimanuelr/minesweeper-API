package com.minesweeper.api.rules;

import java.awt.Point;
import java.util.Random;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minesweeper.api.domain.BombLocker;
import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.Locker;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.domain.enums.LockerType;

@Rule(name = "AddMines", description = "Add mines to the board in random points", priority = 1)
public class AddMines {

	private final Logger log = LoggerFactory.getLogger(AddMines.class);

	@Condition
	public boolean minesHaveNotBeenSet(@Fact("game") Game game) {
		return true;
	}
	
	@Action
	public void setBombsInRandomLockers(@Fact("game") Game game) {
		Random random = new Random();
	    int mines = game.getMinesCount();
	    
	    while (mines > 0) {
	      int x1 = random.nextInt(game.getX() - 1);
	      int y1 = random.nextInt(game.getY() - 1);
	      Locker locker = game.getLockers().get(y1).get(x1);
	      if (locker == null || !LockerType.BOMB.equals(locker.getType())) {
	    	Locker bomb = new BombLocker(new Point(x1, y1));
	    	game.getLockers().get(y1).set(x1, bomb);
	        game.getMinesLocations().add(bomb);
	        mines--;
	      }
	    }
	}
	
}
