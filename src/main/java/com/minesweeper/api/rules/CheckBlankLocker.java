package com.minesweeper.api.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.domain.enums.LockerType;

@Rule(name = "CheckBlankLocker", description = "Check if a given Point has a blank locker and shows all their neighboring lockers")
public class CheckBlankLocker {
	
	private final Logger log = LoggerFactory.getLogger(CheckBlankLocker.class);
	
	@Condition
	public boolean isExposeRequestLocker(@Fact("request") LockerRequest lockerRequest, @Fact("game") Game game) {
		return !game.isLost() && lockerRequest.isExposed();
	}
	
	@Action
	public void showBlankNeighboringLockers(@Fact("request") LockerRequest lockerRequest, @Fact("game") Game game) {
		int x = lockerRequest.getX();
		int y = lockerRequest.getY();
		for (int yOffset = -1; yOffset < 2; yOffset++) {
			for (int xOffset = -1; xOffset < 2; xOffset++) {
				int j = y + yOffset;
				int i = x + xOffset;
		        if (game.isValidCell(i, j)) {
		          if (!LockerType.BOMB.equals(game.getLockers().get(j).get(i).getType()) 
		        		  && !game.getLockers().get(j).get(i).isExposed()) {
		        	game.getLockers().get(j).get(i).setExposed(Boolean.TRUE);
		            if (LockerType.BLANK.equals(game.getLockers().get(j).get(i).getType())) {
		            	showBlankNeighboringLockers(new LockerRequest(LockerType.BLANK, i, j), game);
		            }
		          }
		        }
			}
		}
	}

}
