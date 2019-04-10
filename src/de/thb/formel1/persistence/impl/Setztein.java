package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the setztein database table.
 * 
 */
@Entity
@Table(name="setztein")
@NamedQuery(name="Setztein.findAll", query="SELECT s FROM Setztein s")
public class Setztein implements Serializable {
	private static final long serialVersionUID = 1L;
	private SetzteinPK id;
	private BigDecimal punkte;
	private Rennfahrer rennfahrer;
	private Rennstall rennstall;

	public Setztein() {
	}


	@EmbeddedId
	public SetzteinPK getId() {
		return this.id;
	}

	public void setId(SetzteinPK id) {
		this.id = id;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getPunkte() {
		return this.punkte;
	}

	public void setPunkte(BigDecimal punkte) {
		this.punkte = punkte;
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


	//bi-directional many-to-one association to Rennstall
	@ManyToOne
	@JoinColumn(name="RennstallID", nullable=false, insertable=false, updatable=false)
	public Rennstall getRennstall() {
		return this.rennstall;
	}

	public void setRennstall(Rennstall rennstall) {
		this.rennstall = rennstall;
	}

}