package com.minesweeper.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.minesweeper.api.domain.enums.GameStatus;

/**
 * A Game.
 */
@Document(collection = "game")
public class Game implements Serializable {

    private static final String DEFAULT_NAME = "DEFAULT NAME ";
	private static final Integer DEFAULT_LEGTH_X = 9;
    private static final Integer DEFAULT_LEGTH_Y = 9;
    private static final Integer DEFAULT_MINES_COUNT = 6;

	private static final long serialVersionUID = 1L;
	
    @Id
    private String id;

    @Field("name")
    private String name;
    
    @Field("legthX")
    private Integer x;

    @Field("legthY")
    private Integer y;
    
    @Field("lockers")
    private List<List<Locker>> lockers;
    
    @Field("minesCount")
    private Integer minesCount;

    @Field("minesLocations")
    private List<Locker> minesLocations;
    
    @Field("status")
    private GameStatus status;
    

    public Game() {
		super();
		this.name = DEFAULT_NAME + String.format("%.2f", Math.random() * 10);
		this.x = DEFAULT_LEGTH_X;
		this.y = DEFAULT_LEGTH_Y;
		this.lockers = new ArrayList<List<Locker>>(DEFAULT_LEGTH_Y);
		for(int i = 0; i < DEFAULT_LEGTH_Y; i++) {
			List<Locker> list = new ArrayList<Locker>(DEFAULT_LEGTH_X);
			for(int j = 0; j < DEFAULT_LEGTH_X; j++) {
				list.add(j, new Locker());
			}
			this.lockers.add(i, list);
        }
		this.minesCount = DEFAULT_MINES_COUNT;
		this.minesLocations = new ArrayList<Locker>(DEFAULT_MINES_COUNT);
		this.status = GameStatus.IN_PLAY;
	}
    
    public Game(int rows, int columns, int mines, String name) {
		super();
		this.name = name;
		this.x = columns;
		this.y = rows;
		this.lockers = new ArrayList<List<Locker>>(this.y);
		for(int i = 0; i < this.y; i++) {
			List<Locker> list = new ArrayList<Locker>(this.x);
			for(int j = 0; j < this.x; j++) {
				list.add(j, new Locker());
			}
			this.lockers.add(i, list);
        }
		this.minesCount = mines;
		this.minesLocations = new ArrayList<Locker>(mines);
		this.status = GameStatus.IN_PLAY;
	}

	public boolean isValidCell(int x, int y) {
	    if (x >= 0 && y >= 0 && x < this.x && y < this.y) {
	      return true;
	    }
	    return false;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Game name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        return id != null && id.equals(((Game) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Game{" +
            "id=" + getId() +
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

	public List<Locker> getMinesLocations() {
		return minesLocations;
	}

	public void setMinesLocations(List<Locker> minesLocations) {
		this.minesLocations = minesLocations;
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
	
	public boolean isLost() {
		return GameStatus.LOST.equals(this.status);
	}
	
}
