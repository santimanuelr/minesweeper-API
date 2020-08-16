package com.minesweeper.api.domain;

import java.awt.Point;

import com.minesweeper.api.domain.enume.LockerType;

public class BombLocker extends Locker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BombLocker() {
		// TODO Auto-generated constructor stub
		this.type = LockerType.BOMB;
	}

	public BombLocker(Point point) {
		// TODO Auto-generated constructor stub
		super(point);
		this.type = LockerType.BOMB;
	}

}
