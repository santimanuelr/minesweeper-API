package com.minesweeper.api.rules;

import java.awt.Point;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.Locker;
import com.minesweeper.api.domain.NumberLocker;
import com.minesweeper.api.domain.enums.LockerType;

@Rule(name = "PlaceNumbers", description = "Put numbers in the lockers near the mines", priority = 2)
public class PlaceNumbers {
	
private final Logger log = LoggerFactory.getLogger(AddMines.class);
	
	@Condition
	public boolean numbersHaveNotBeenSet(@Fact("game") Game game) {
		return true;
	}
	
	@Action
	public void putNumbersInBombNeighboringLockers(@Fact("game") Game game) {
		for (int i = 0; i < game.getY(); i++) {
			for (int j = 0; j < game.getX(); j++) {
		    	Locker locker = game.getLockers().get(i).get(j);
		    	if (LockerType.BOMB.equals(locker.getType())) {
		          continue;
		        }
		        int counter = 0;
		        for (int xOffset = -1; xOffset < 2; xOffset++) {
		          for (int yOffset = -1; yOffset < 2; yOffset++) {
		            int tmpX = j + xOffset;
		            int tmpY = i + yOffset;
		            if (game.isValidCell(tmpX, tmpY) && LockerType.BOMB.equals(game.getLockers().get(tmpY).get(tmpX).getType())) {
		              counter++;
		            }
		          }
		        }
		        if (counter == 0) {
		        	locker.setType(LockerType.BLANK);
				} else {
					locker = new NumberLocker(counter);
				}
		        locker.setPoint(new Point(j, i));
		        game.getLockers().get(i).set(j, locker);
	        }
	    }
	}

}
