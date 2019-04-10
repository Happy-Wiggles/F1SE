package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the land database table.
 * 
 */
@Entity
@Table(name="land")
@NamedQuery(name="Land.findAll", query="SELECT l FROM Land l")
public class Land implements Serializable {
	private static final long serialVersionUID = 1L;
	private int landID;
	private byte[] flagge;
	private String name;
	private List<Rennfahrer> rennfahrers;
	private List<Rennstrecke> rennstreckes;

	public Land() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getLandID() {
		return this.landID;
	}

	public void setLandID(int landID) {
		this.landID = landID;
	}


	@Lob
	public byte[] getFlagge() {
		return this.flagge;
	}

	public void setFlagge(byte[] flagge) {
		this.flagge = flagge;
	}


	@Column(nullable=false, length=40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Rennfahrer
	@OneToMany(mappedBy="land")
	public List<Rennfahrer> getRennfahrers() {
		return this.rennfahrers;
	}

	public void setRennfahrers(List<Rennfahrer> rennfahrers) {
		this.rennfahrers = rennfahrers;
	}

	public Rennfahrer addRennfahrer(Rennfahrer rennfahrer) {
		getRennfahrers().add(rennfahrer);
		rennfahrer.setLand(this);

		return rennfahrer;
	}

	public Rennfahrer removeRennfahrer(Rennfahrer rennfahrer) {
		getRennfahrers().remove(rennfahrer);
		rennfahrer.setLand(null);

		return rennfahrer;
	}


	//bi-directional many-to-one association to Rennstrecke
	@OneToMany(mappedBy="land")
	public List<Rennstrecke> getRennstreckes() {
		return this.rennstreckes;
	}

	public void setRennstreckes(List<Rennstrecke> rennstreckes) {
		this.rennstreckes = rennstreckes;
	}

	public Rennstrecke addRennstrecke(Rennstrecke rennstrecke) {
		getRennstreckes().add(rennstrecke);
		rennstrecke.setLand(this);

		return rennstrecke;
	}

	public Rennstrecke removeRennstrecke(Rennstrecke rennstrecke) {
		getRennstreckes().remove(rennstrecke);
		rennstrecke.setLand(null);

		return rennstrecke;
	}

}