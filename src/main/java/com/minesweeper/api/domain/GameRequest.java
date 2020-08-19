package com.minesweeper.api.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.minesweeper.api.domain.enums.GameStatus;

/**
 * A Game Request
 */
public class GameRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
    private String name;
    
    @Max(value = 30l, message = "not valid column value") private Integer x;

    @Max(value = 16l, message = "not valid row value") private Integer y;
    
    private List<List<Locker>> lockers;
    
    private Integer minesCount;

    private GameStatus status;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GameRequest name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Game{" +
            ", name='" + getName() + "'" +
            "}";
    }

	public List<List<Locker>> getLockers() {
		return lockers;
	}

	public void setLockers(List<List<Locker>> lockers) {
		this.lockers = lockers;
	}

	public Integer getMinesCount() {
		return minesCount;
	}

	public void setMinesCount(Integer minesCount) {
		this.minesCount = minesCount;
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

	public GameStatus getGameStatus() {
		return status;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.status = gameStatus;
	}
	
}
