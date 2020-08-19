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
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.domain.enums.GameStatus;

@Rule(name = "MarkFlagLocker", description = "Mark a flag in the given locker")
public class MarkFlagLocker {
	
	private final Logger log = LoggerFactory.getLogger(MarkFlagLocker.class);
	
	@Condition
	public boolean canMarkFlagLocker(@Fact("request") LockerRequest lockerReq, @Fact("game") Game game) {
		return !game.isLost() && !game.getLockers().get(lockerReq.getY()).get(lockerReq.getX()).isExposed() 
				&& lockerReq.isFlag();
	}
	
	@Action
	public void markFlagLocker(@Fact("request") LockerRequest lockerReq, @Fact("game") Game game) {
		Locker locker = game.getLockers().get(lockerReq.getY()).get(lockerReq.getX());
		locker.setFlag(Boolean.TRUE);
		locker.setQuestion(Boolean.FALSE);
		int x = lockerReq.getX();
		int y = lockerReq.getY();
		Point point = new Point(x, y);
		game.getMinesLocations().stream()
				.filter(l -> l.getPoint().equals(point))
				.findFirst()
				.ifPresent(l -> l.setFlag(Boolean.TRUE));
		game.getLockers().get(lockerReq.getY()).set(lockerReq.getX(), locker);
		if (!game.getMinesLocations().stream().filter(l -> !l.isFlag()).findAny().isPresent()) {
			game.setGameStatus(GameStatus.WON);
		}
	}

}
