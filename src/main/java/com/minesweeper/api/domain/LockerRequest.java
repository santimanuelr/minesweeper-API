package com.minesweeper.api.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.minesweeper.api.domain.enums.LockerType;
import com.minesweeper.api.domain.validators.ValidLockerRequest;

@ValidLockerRequest
public class LockerRequest {
	
    @NotBlank private String idGame;
	
	private LockerType lockerType;
	@NotNull private Integer x;
	@NotNull private Integer y;
	
	private boolean exposed = false;
    
    private boolean question = false;
    
    private boolean flag = false;
    
    private boolean uncheck = false;
    
    
    	
	public LockerRequest() {
		super();
	}

	public LockerRequest(Integer y, Integer x) {
		super();
		this.x = x;
		this.y = y;
	}

	public LockerRequest(LockerType lockerType, int x, int y) {
		this.lockerType = lockerType;
		this.x = x;
		this.y = y;
	}
	
	public LockerType getLockerType() {
		return lockerType;
	}
	public void setLockerType(LockerType lockerType) {
		this.lockerType = lockerType;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public boolean isQuestion() {
		return question;
	}

	public void setQuestion(boolean question) {
		this.question = question;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isUncheck() {
		return uncheck;
	}

	public void setUncheck(boolean uncheck) {
		this.uncheck = uncheck;
	}

	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

}
