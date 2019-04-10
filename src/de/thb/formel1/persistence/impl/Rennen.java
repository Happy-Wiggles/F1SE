package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rennen database table.
 * 
 */
@Entity
@Table(name="rennen")
@NamedQuery(name="Rennen.findAll", query="SELECT r FROM Rennen r")
public class Rennen implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date datum;
	private List<Gefahrenvon> gefahrenvons;
	private Rennfahrer poleRennfahrer;
	private Rennfahrer rennfahrerSchnellsteRunde;
	private Rennstrecke rennstrecke;
	private Saison saison;

	public Rennen() {
	}


	@Id
	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}


	//bi-directional many-to-one association to Gefahrenvon
	@OneToMany(mappedBy="rennen")
	public List<Gefahrenvon> getGefahrenvons() {
		return this.gefahrenvons;
	}

	public void setGefahrenvons(List<Gefahrenvon> gefahrenvons) {
		this.gefahrenvons = gefahrenvons;
	}

	public Gefahrenvon addGefahrenvon(Gefahrenvon gefahrenvon) {
		getGefahrenvons().add(gefahrenvon);
		gefahrenvon.setRennen(this);

		return gefahrenvon;
	}

	public Gefahrenvon removeGefahrenvon(Gefahrenvon gefahrenvon) {
		getGefahrenvons().remove(gefahrenvon);
		gefahrenvon.setRennen(null);

		return gefahrenvon;
	}


	//bi-directional many-to-one association to Rennfahrer
	@ManyToOne
	@JoinColumn(name="PoleFahrerID")
	public Rennfahrer getPoleRennfahrer() {
		return this.poleRennfahrer;
	}

	public void setPoleRennfahrer(Rennfahrer poleRennfahrer) {
		this.poleRennfahrer = poleRennfahrer;
	}


	//bi-directional many-to-one association to Rennfahrer
	@ManyToOne
	@JoinColumn(name="SchnellsteRundeID")
	public Rennfahrer getRennfahrerSchnellsteRunde() {
		return this.rennfahrerSchnellsteRunde;
	}

	public void setRennfahrerSchnellsteRunde(Rennfahrer rennfahrerSchnellsteRunde) {
		this.rennfahrerSchnellsteRunde = rennfahrerSchnellsteRunde;
	}


	//bi-directional many-to-one association to Rennstrecke
	@ManyToOne
	@JoinColumn(name="StreckenID", nullable=false)
	public Rennstrecke getRennstrecke() {
		return this.rennstrecke;
	}

	public void setRennstrecke(Rennstrecke rennstrecke) {
		this.rennstrecke = rennstrecke;
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

}