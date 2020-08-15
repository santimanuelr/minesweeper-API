package com.minesweeper.api.domain;

import java.awt.Point;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.minesweeper.api.domain.enume.LockerType;

/**
 * A Locker.
 */
//@Document(collection = "locker")
public class Locker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("type")
    protected LockerType type;
    
    @Field("point")
    private Point point;

    private boolean revealed = false;
    
    
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

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
}
