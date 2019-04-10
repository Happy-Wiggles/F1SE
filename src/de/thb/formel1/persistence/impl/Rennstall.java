package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the rennstall database table.
 * 
 */
@Entity
@Table(name="rennstall")
@NamedQuery(name="Rennstall.findAll", query="SELECT r FROM Rennstall r")
public class Rennstall implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rennstallID;
	private String name;
	private BigDecimal punkte;
	private Saison saison;
	private List<Setztein> setzteins;

	public Rennstall() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getRennstallID() {
		return this.rennstallID;
	}

	public void setRennstallID(int rennstallID) {
		this.rennstallID = rennstallID;
	}


	@Column(nullable=false, length=40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getPunkte() {
		return this.punkte;
	}

	public void setPunkte(BigDecimal punkte) {
		this.punkte = punkte;
	}


	//bi-directional many-to-one association to Saison
	@ManyToOne
	@JoinColumn(name="Jahr", nullable=false)
	public Saison getSaison() {
		return this.saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}


	//bi-directional many-to-one association to Setztein
	@OneToMany(mappedBy="rennstall")
	public List<Setztein> getSetzteins() {
		return this.setzteins;
	}

	public void setSetzteins(List<Setztein> setzteins) {
		this.setzteins = setzteins;
	}

	public Setztein addSetztein(Setztein setztein) {
		getSetzteins().add(setztein);
		setztein.setRennstall(this);

		return setztein;
	}

	public Setztein removeSetztein(Setztein setztein) {
		getSetzteins().remove(setztein);
		setztein.setRennstall(null);

		return setztein;
	}

}