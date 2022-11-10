package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase qui modelise un objet JourHoraire
 * 
 * @author Alao Bamishe
 * @author Christine Dos Santos
 *
 */
@Entity
@Table(name = "jour_horaire")
public class JourHoraire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jour_horaire_id")
	private Integer jourHoraireId;

	private String matiere;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jour_id")
	private Jour jour;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "horaire_id")
	private Horaire horaire;

	public JourHoraire() {

	}

	public JourHoraire(String matiere, Jour jour, Horaire horaire) {
		super();
		this.matiere = matiere;
		this.jour = jour;
		this.horaire = horaire;
	}

	public JourHoraire(Jour jour, Horaire horaire, String matiere) {
		super();
		this.matiere = matiere;
		this.jour = jour;
		this.horaire = horaire;
	}

	public Integer getJourHoraireId() {
		return jourHoraireId;
	}

	public void setJourHoraireId(Integer jourHoraireId) {
		this.jourHoraireId = jourHoraireId;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public Jour getJour() {
		return jour;
	}

	public void setJour(Jour jour) {
		this.jour = jour;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	@Override
	public String toString() {
		return "JourHoraire [jourHoraireId=" + jourHoraireId + ", matiere=" + matiere + ", jour=" + jour + ", horaire="
				+ horaire + "]";
	}

}
