package com.minesweeper.api.domain;

import java.awt.Point;

import com.minesweeper.api.domain.enums.LockerType;

public class BombLocker extends Locker {

	private static final long serialVersionUID = 1L;
	
	public BombLocker() {
		this.type = LockerType.BOMB;
	}

	public BombLocker(Point point) {
		super(point);
		this.type = LockerType.BOMB;
	}

}
