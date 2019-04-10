package de.thb.formel1.persistence.impl;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rennfahrer database table.
 * 
 */
@Entity
@Table(name="rennfahrer")
@NamedQuery(name="Rennfahrer.findAll", query="SELECT r FROM Rennfahrer r")
public class Rennfahrer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rennfahrerID;
	private Date geburtstag;
	private String name;
	private BigDecimal polePositions;
	private BigDecimal punkte;
	private BigDecimal schnellsteRunden;
	private BigDecimal siege;
	private String vorname;
	private List<Gefahrenvon> gefahrenvons;
	private List<Rennen> rennenWithPoles;
	private List<Rennen> rennenWithSchnellsteRunde;
	private Land land;
	private List<Setztein> setzteins;

	public Rennfahrer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getRennfahrerID() {
		return this.rennfahrerID;
	}

	public void setRennfahrerID(int rennfahrerID) {
		this.rennfahrerID = rennfahrerID;
	}


	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	public Date getGeburtstag() {
		return this.geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}


	@Column(nullable=false, length=30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false, precision=10)
	public BigDecimal getPolePositions() {
		return this.polePositions;
	}

	public void setPolePositions(BigDecimal polePositions) {
		this.polePositions = polePositions;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getPunkte() {
		return this.punkte;
	}

	public void setPunkte(BigDecimal punkte) {
		this.punkte = punkte;
	}


	@Column(nullable=false, precision=10)
	public BigDecimal getSchnellsteRunden() {
		return this.schnellsteRunden;
	}

	public void setSchnellsteRunden(BigDecimal schnellsteRunden) {
		this.schnellsteRunden = schnellsteRunden;
	}


	@Column(nullable=false, precision=10)
	public BigDecimal getSiege() {
		return this.siege;
	}

	public void setSiege(BigDecimal siege) {
		this.siege = siege;
	}


	@Column(nullable=false, length=30)
	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	//bi-directional many-to-one association to Gefahrenvon
	@OneToMany(mappedBy="rennfahrer")
	public List<Gefahrenvon> getGefahrenvons() {
		return this.gefahrenvons;
	}

	public void setGefahrenvons(List<Gefahrenvon> gefahrenvons) {
		this.gefahrenvons = gefahrenvons;
	}

	public Gefahrenvon addGefahrenvon(Gefahrenvon gefahrenvon) {
		getGefahrenvons().add(gefahrenvon);
		gefahrenvon.setRennfahrer(this);

		return gefahrenvon;
	}

	public Gefahrenvon removeGefahrenvon(Gefahrenvon gefahrenvon) {
		getGefahrenvons().remove(gefahrenvon);
		gefahrenvon.setRennfahrer(null);

		return gefahrenvon;
	}


	//bi-directional many-to-one association to Rennen
	@OneToMany(mappedBy="poleRennfahrer")
	public List<Rennen> getRennenWithPoles() {
		return this.rennenWithPoles;
	}

	public void setRennenWithPoles(List<Rennen> rennenWithPoles) {
		this.rennenWithPoles = rennenWithPoles;
	}

	public Rennen addRennenWithPole(Rennen rennenWithPole) {
		getRennenWithPoles().add(rennenWithPole);
		rennenWithPole.setPoleRennfahrer(this);

		return rennenWithPole;
	}

	public Rennen removeRennenWithPole(Rennen rennenWithPole) {
		getRennenWithPoles().remove(rennenWithPole);
		rennenWithPole.setPoleRennfahrer(null);

		return rennenWithPole;
	}


	//bi-directional many-to-one association to Rennen
	@OneToMany(mappedBy="rennfahrerSchnellsteRunde")
	public List<Rennen> getRennenWithSchnellsteRunde() {
		return this.rennenWithSchnellsteRunde;
	}

	public void setRennenWithSchnellsteRunde(List<Rennen> rennenWithSchnellsteRunde) {
		this.rennenWithSchnellsteRunde = rennenWithSchnellsteRunde;
	}

	public Rennen addRennenWithSchnellsteRunde(Rennen rennenWithSchnellsteRunde) {
		getRennenWithSchnellsteRunde().add(rennenWithSchnellsteRunde);
		rennenWithSchnellsteRunde.setRennfahrerSchnellsteRunde(this);

		return rennenWithSchnellsteRunde;
	}

	public Rennen removeRennenWithSchnellsteRunde(Rennen rennenWithSchnellsteRunde) {
		getRennenWithSchnellsteRunde().remove(rennenWithSchnellsteRunde);
		rennenWithSchnellsteRunde.setRennfahrerSchnellsteRunde(null);

		return rennenWithSchnellsteRunde;
	}


	//bi-directional many-to-one association to Land
	@ManyToOne
	@JoinColumn(name="Nationalitaet", nullable=false)
	public Land getLand() {
		return this.land;
	}

	public void setLand(Land land) {
		this.land = land;
	}


	//bi-directional many-to-one association to Setztein
	@OneToMany(mappedBy="rennfahrer")
	public List<Setztein> getSetzteins() {
		return this.setzteins;
	}

	public void setSetzteins(List<Setztein> setzteins) {
		this.setzteins = setzteins;
	}

	public Setztein addSetztein(Setztein setztein) {
		getSetzteins().add(setztein);
		setztein.setRennfahrer(this);

		return setztein;
	}

	public Setztein removeSetztein(Setztein setztein) {
		getSetzteins().remove(setztein);
		setztein.setRennfahrer(null);

		return setztein;
	}

}