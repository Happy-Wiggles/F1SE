package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rennstrecke database table.
 * 
 */
@Entity
@Table(name="rennstrecke")
@NamedQuery(name="Rennstrecke.findAll", query="SELECT r FROM Rennstrecke r")
public class Rennstrecke implements Serializable {
	private static final long serialVersionUID = 1L;
	private int streckenID;
	private String name;
	private List<Rennen> rennens;
	private Land land;

	public Rennstrecke() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getStreckenID() {
		return this.streckenID;
	}

	public void setStreckenID(int streckenID) {
		this.streckenID = streckenID;
	}


	@Column(nullable=false, length=40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Rennen
	@OneToMany(mappedBy="rennstrecke")
	public List<Rennen> getRennens() {
		return this.rennens;
	}

	public void setRennens(List<Rennen> rennens) {
		this.rennens = rennens;
	}

	public Rennen addRennen(Rennen rennen) {
		getRennens().add(rennen);
		rennen.setRennstrecke(this);

		return rennen;
	}

	public Rennen removeRennen(Rennen rennen) {
		getRennens().remove(rennen);
		rennen.setRennstrecke(null);

		return rennen;
	}


	//bi-directional many-to-one association to Land
	@ManyToOne
	@JoinColumn(name="LandID", nullable=false)
	public Land getLand() {
		return this.land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

}