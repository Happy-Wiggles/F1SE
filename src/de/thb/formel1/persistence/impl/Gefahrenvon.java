package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gefahrenvon database table.
 * 
 */
@Entity
@Table(name="gefahrenvon")
@NamedQuery(name="Gefahrenvon.findAll", query="SELECT g FROM Gefahrenvon g")
public class Gefahrenvon implements Serializable {
	private static final long serialVersionUID = 1L;
	private GefahrenvonPK id;
	private BigDecimal platz;
	private Rennen rennen;
	private Rennfahrer rennfahrer;

	public Gefahrenvon() {
	}


	@EmbeddedId
	public GefahrenvonPK getId() {
		return this.id;
	}

	public void setId(GefahrenvonPK id) {
		this.id = id;
	}


	@Column(precision=10)
	public BigDecimal getPlatz() {
		return this.platz;
	}

	public void setPlatz(BigDecimal platz) {
		this.platz = platz;
	}


	//bi-directional many-to-one association to Rennen
	@ManyToOne
	@JoinColumn(name="Datum", nullable=false, insertable=false, updatable=false)
	public Rennen getRennen() {
		return this.rennen;
	}

	public void setRennen(Rennen rennen) {
		this.rennen = rennen;
	}


	//bi-directional many-to-one association to Rennfahrer
	@ManyToOne
	@JoinColumn(name="RennfahrerID", nullable=false, insertable=false, updatable=false)
	public Rennfahrer getRennfahrer() {
		return this.rennfahrer;
	}

	public void setRennfahrer(Rennfahrer rennfahrer) {
		this.rennfahrer = rennfahrer;
	}

}