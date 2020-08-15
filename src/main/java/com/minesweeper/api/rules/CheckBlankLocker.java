package com.minesweeper.api.rules;

import java.awt.Point;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.domain.enume.LockerType;

@Rule(name = "CheckBlankLocker", description = "Check if a given Point has a blank locker an shows all their neighboring lockers")
public class CheckBlankLocker {
	
	private final Logger log = LoggerFactory.getLogger(CheckBlankLocker.class);
	
	@Condition
	public boolean isBlankLocker(@Fact("request") LockerRequest lockerRequest, @Fact("game") Game game) {
		return true;
	}
	
	@Action
	public void showBlankNeighboringLockers(@Fact("request") LockerRequest lockerRequest, @Fact("game") Game game) {
		Point point = lockerRequest.getPoint();
		for (int yOffset = -1; yOffset < 2; yOffset++) {
			for (int xOffset = -1; xOffset < 2; xOffset++) {
				int j = point.y + yOffset;
				int i = point.x + xOffset;
		        if (game.isValidCell(i, j)) {
		          if (!LockerType.BOMB.equals(game.getLockers().get(j).get(i).getType()) 
		        		  && !game.getLockers().get(j).get(i).isRevealed()) {
		        	game.getLockers().get(j).get(i).setRevealed(Boolean.TRUE);
		            //response.add(new CellStatusResponse(i, j, this.cells[i][j]));
		            if (LockerType.BLANK.equals(game.getLockers().get(j).get(i).getType())) {
		            	showBlankNeighboringLockers(new LockerRequest(LockerType.BLANK, i, j), game);
		            }
		          }
		        }
			}
		}
	}

}
