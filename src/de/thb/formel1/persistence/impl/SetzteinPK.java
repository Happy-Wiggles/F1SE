package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the setztein database table.
 * 
 */
@Embeddable
public class SetzteinPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int rennstallID;
	private int rennfahrerID;

	public SetzteinPK() {
	}

	@Column(insertable=false, unique=true, nullable=false)
	public int getRennstallID() {
		return this.rennstallID;
	}
	public void setRennstallID(int rennstallID) {
		this.rennstallID = rennstallID;
	}

	@Column(insertable=false, unique=true, nullable=false)
	public int getRennfahrerID() {
		return this.rennfahrerID;
	}
	public void setRennfahrerID(int rennfahrerID) {
		this.rennfahrerID = rennfahrerID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SetzteinPK)) {
			return false;
		}
		SetzteinPK castOther = (SetzteinPK)other;
		return 
			(this.rennstallID == castOther.rennstallID)
			&& (this.rennfahrerID == castOther.rennfahrerID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rennstallID;
		hash = hash * prime + this.rennfahrerID;
		
		return hash;
	}
}