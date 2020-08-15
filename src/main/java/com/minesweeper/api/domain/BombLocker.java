package com.minesweeper.api.domain;

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

}
