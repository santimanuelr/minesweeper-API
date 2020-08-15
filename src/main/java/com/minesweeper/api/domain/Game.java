package com.minesweeper.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.minesweeper.api.domain.enume.LockerType;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Game.
 */
@Document(collection = "game")
public class Game implements Serializable {

    private static final String DEFAULT_NAME = "DEFAULT NAME";
	private static final Integer DEFAULT_LEGTH_X = 9;
    private static final Integer DEFAULT_LEGTH_Y = 9;
    private static final Integer DEFAULT_MINES_COUNT = 3;

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
    
    

    public Game() {
		super();
		this.name = DEFAULT_NAME + Math.random() * 10;
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
		addMines();
		placeNumbers();
	}
    

	private void addMines() {
		
		Random random = new Random();
	    int mines = this.minesCount;
	    
	    while (mines > 0) {
	      int x1 = random.nextInt(this.x - 1);
	      int y1 = random.nextInt(this.y - 1);
	      Locker locker = this.lockers.get(y1).get(x1);
	      if (locker == null || !LockerType.BOMB.equals(locker.getType())) {
	    	Locker bomb = new BombLocker();
	    	this.lockers.get(y1).set(x1, bomb);
	        this.minesLocations.add(bomb);
	        mines--;
	      }
	    }
	    
	}
	
	public void placeNumbers() {
	    for (int i = 0; i < this.y; i++) {
	      for (int j = 0; j < this.x; j++) {
	    	Locker locker = this.lockers.get(i).get(j);
	    	if (LockerType.BOMB.equals(locker.getType())) {
	          continue;
	        }
	        int counter = 0;
	        for (int xOffset = -1; xOffset < 2; xOffset++) {
	          for (int yOffset = -1; yOffset < 2; yOffset++) {
	            int tmpX = i + xOffset;
	            int tmpY = j + yOffset;
	            if (isValidCell(tmpX, tmpY) && LockerType.BOMB.equals(this.lockers.get(tmpY).get(tmpX).getType())) {
	              counter++;
	            }
	          }
	        }
	        if (counter == 0) {
	        	locker.setType(LockerType.BLANK);
			} else {
				locker = new NumberLocker(counter);
			}
	        locker.setPoint(new Point(j, i));
	        this.lockers.get(i).set(j, locker);
	      }
	    }
	}

	boolean isValidCell(int x, int y) {
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
}
