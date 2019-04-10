package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the saison database table.
 * 
 */
@Entity
@Table(name="saison")
@NamedQuery(name="Saison.findAll", query="SELECT s FROM Saison s")
public class Saison implements Serializable {
	private static final long serialVersionUID = 1L;
	private int jahr;
	private List<Rennen> rennens;
	private List<Rennstall> rennstalls;

	public Saison() {
	}


	@Id
	@Column(unique=true, nullable=false, precision=10)
	public int getJahr() {
		return this.jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}


	//bi-directional many-to-one association to Rennen
	@OneToMany(mappedBy="saison")
	public List<Rennen> getRennens() {
		return this.rennens;
	}

	public void setRennens(List<Rennen> rennens) {
		this.rennens = rennens;
	}

	public Rennen addRennen(Rennen rennen) {
		getRennens().add(rennen);
		rennen.setSaison(this);

		return rennen;
	}

	public Rennen removeRennen(Rennen rennen) {
		getRennens().remove(rennen);
		rennen.setSaison(null);

		return rennen;
	}


	//bi-directional many-to-one association to Rennstall
	@OneToMany(mappedBy="saison")
	public List<Rennstall> getRennstalls() {
		return this.rennstalls;
	}

	public void setRennstalls(List<Rennstall> rennstalls) {
		this.rennstalls = rennstalls;
	}

	public Rennstall addRennstall(Rennstall rennstall) {
		getRennstalls().add(rennstall);
		rennstall.setSaison(this);

		return rennstall;
	}

	public Rennstall removeRennstall(Rennstall rennstall) {
		getRennstalls().remove(rennstall);
		rennstall.setSaison(null);

		return rennstall;
	}

}