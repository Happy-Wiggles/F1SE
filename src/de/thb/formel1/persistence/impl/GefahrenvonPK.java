package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gefahrenvon database table.
 * 
 */
@Embeddable
public class GefahrenvonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int rennfahrerID;
	private java.util.Date datum;

	public GefahrenvonPK() {
	}

	@Column(insertable=false, unique=true, nullable=false)
	public int getRennfahrerID() {
		return this.rennfahrerID;
	}
	public void setRennfahrerID(int rennfahrerID) {
		this.rennfahrerID = rennfahrerID;
	}

	@Temporal(TemporalType.DATE)
	@Column(insertable=false, unique=true, nullable=false)
	public java.util.Date getDatum() {
		return this.datum;
	}
	public void setDatum(java.util.Date datum) {
		this.datum = datum;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GefahrenvonPK)) {
			return false;
		}
		GefahrenvonPK castOther = (GefahrenvonPK)other;
		return 
			(this.rennfahrerID == castOther.rennfahrerID)
			&& this.datum.equals(castOther.datum);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rennfahrerID;
		hash = hash * prime + this.datum.hashCode();
		
		return hash;
	}
}