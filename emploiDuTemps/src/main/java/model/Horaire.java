package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase qui modelise un objet Horaire
 * 
 * @author Alao Bamishe
 * @author Christine Dos Santos
 *
 */
@Entity
public class Horaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horaire_id")
	private Integer horaireId;

	private String intervalle;

	@OneToMany(mappedBy = "jour")
	private List<JourHoraire> listJourHoraire;

	public Horaire() {
	}

	public Horaire(Integer horaireId, String intervalle) {
		this.horaireId = horaireId;
		this.intervalle = intervalle;
	}

	public Integer getHoraireId() {
		return horaireId;
	}

	public void setHoraireId(Integer horaireId) {
		this.horaireId = horaireId;
	}

	public String getIntervalle() {
		return intervalle;
	}

	public void setIntervalle(String intervalle) {
		this.intervalle = intervalle;
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
		return "Horaire [horaireId=" + horaireId + ", intervalle=" + intervalle + "]";
	}

}
