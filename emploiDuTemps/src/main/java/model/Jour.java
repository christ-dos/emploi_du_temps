package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe qui modelise objet Jour
 * 
 * @author Christine Dos Santos
 * @author bamnishé Alao
 *
 */
@Entity
public class Jour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jour_id")
	private Integer jourId;
	private String nom;

	@OneToMany(mappedBy = "jour")
	private List<JourHoraire> listJourHoraire = new ArrayList<>();

	public Jour() {
	}

	public Integer getJourId() {
		return jourId;
	}

	public void setJourId(Integer jourId) {
		this.jourId = jourId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<JourHoraire> getListJourHoraire() {
		return listJourHoraire;
	}

	public void setListJourHoraire(List<JourHoraire> listJourHoraire) {
		this.listJourHoraire = listJourHoraire;
	}

	/**
	 * Methode de mise a jour du tableau listHoraire
	 * 
	 * @param jourhoraire un objet de type {@link JourHoraire}
	 */
	public void ajouterJourHoraire(JourHoraire jourhoraire) {
		listJourHoraire.add(jourhoraire);
	}

	@Override
	public String toString() {
		return "Jour [jourId=" + jourId + ", nom=" + nom + "]";
	}

}
