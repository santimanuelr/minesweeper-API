package com.minesweeper.api.domain;

import java.awt.Point;
import java.io.Serializable;

import com.minesweeper.api.domain.enume.LockerType;

/**
 * A Locker.
 */
public class Locker implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    protected LockerType type;
    
    private Point point;

    private boolean exposed;
    
    private boolean question;
    
    private boolean flag;
    
    
    public Locker() {
    	this.exposed = false;
    	this.question = false;
    	this.flag = false;
	}
    
    
    public Locker(Point point) {
    	this.exposed = false;
    	this.question = false;
    	this.flag = false;
    	this.point = point;
	}


	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LockerType getType() {
        return type;
    }

    public Locker type(LockerType type) {
        this.type = type;
        return this;
    }

    public void setType(LockerType type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Locker)) {
            return false;
        }
        return id != null && id.equals(((Locker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Locker{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            "}";
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
	
}
