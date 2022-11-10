package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import exception.MatiereAlreadyExistsException;
import model.JourHoraire;

/**
 * 
 * Classe qui permet la gestion de la BDD
 * 
 * @author Christine Dos Santos
 * @author bamnishé Alao
 *
 */
public class JourHoraireDAO {
	/**
	 * Methode qui permet d'ajouter une matiere dans la table JourHoraire(equivalant
	 * aux cases de notre tableau) si la case est libre
	 * 
	 * @param jourHoraire Objet de type JourHoraire
	 * @see JourHoraire
	 */
	public void ajouterMatiere(JourHoraire jourHoraire) {
		SessionFactory sessionfact = new Configuration().configure().buildSessionFactory();
		Session session = sessionfact.openSession();
		List<JourHoraire> jourHoraires = new ArrayList<JourHoraire>();

		Transaction transaction = session.beginTransaction();

		JourHoraire jourHoraireResult = (JourHoraire) session
				.createQuery("from JourHoraire jh where jh.jour.jourId=" + jourHoraire.getJour().getJourId()
						+ "AND jh.horaire.horaireId=" + jourHoraire.getHoraire().getHoraireId() + "")
				.uniqueResult();

		if (jourHoraireResult != null) {
			if (jourHoraireResult.getMatiere() != null) {
				throw new MatiereAlreadyExistsException("Ce créneau n'est pas libre");
			}
			jourHoraireResult.setMatiere(jourHoraire.getMatiere());
		}

		transaction.commit();
		session.close();
		sessionfact.close();

	}

	/**
	 * Methode qui permet d'afficher tous les relations JourHoraire de la BDD
	 * 
	 * @return liste de @see JourHoraire
	 */
	public List<JourHoraire> afficherTableJourHoraire() {
		Configuration con = new Configuration().configure();
		SessionFactory sessionfact = con.buildSessionFactory();
		Session session = sessionfact.openSession();

		Transaction transaction = session.beginTransaction();
		List<JourHoraire> jourHoraires = new ArrayList<>();

		jourHoraires = (List<JourHoraire>) session.createCriteria(JourHoraire.class).list();

		transaction.commit();
		session.close();
		sessionfact.close();

		return jourHoraires;

	}

	/**
	 * Methode qui permet de reinitialiser l'emploi du temps en effancant les donnes
	 * matiere
	 */
	public void effacerTouteLesMatiereDuTableau() {
		Configuration con = new Configuration().configure();
		SessionFactory sessionfact = con.buildSessionFactory();
		Session session = sessionfact.openSession();

		Transaction transaction = session.beginTransaction();

		ArrayList<JourHoraire> jourHoraires = new ArrayList<>();
		jourHoraires = (ArrayList<JourHoraire>) session.createQuery("from JourHoraire").list();
		for (JourHoraire jourHoraire : jourHoraires) {
			jourHoraire.setMatiere(null);
		}

		transaction.commit();
		session.close();
		sessionfact.close();

	}

}
