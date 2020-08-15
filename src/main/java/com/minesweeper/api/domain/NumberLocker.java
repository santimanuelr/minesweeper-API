package com.minesweeper.api.domain;

import com.minesweeper.api.domain.enume.LockerType;

public class NumberLocker extends Locker {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer number;
	
	
	
	public NumberLocker(Integer number) {
		super();
		this.type = LockerType.NUMBER;
		this.number = number;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	

}
