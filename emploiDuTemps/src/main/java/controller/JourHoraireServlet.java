package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.JourHoraireDAO;
import exception.MatiereAlreadyExistsException;
import model.Horaire;
import model.Jour;
import model.JourHoraire;

/**
 * Servlet qui recoit les infomations du formulaire et qui gére l'ajout des
 * matieres dans l'emploi du temps ainsi que les erreur entrée dans le
 * formulaire
 * 
 * @author Christine Dos Santos
 * @author bamnishé Alao
 */
@WebServlet("/jourhoraireservlet")
@WebListener
public class JourHoraireServlet extends HttpServlet implements ServletRequestListener {
	private static final long serialVersionUID = 1L;

	private static List<String> listHeureDebut;
	private static List<String> listHeureFin;
	private List<JourHoraire> jourHoraires = new ArrayList<>();

	private JourHoraireDAO jourHoraireDAO = new JourHoraireDAO();

	String[] tabDemiHeure = { "00", "30" };
	String[][] emploiDuTemps = new String[22][6];

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JourHoraireServlet() {
	}

	/**
	 * methode qui permet d'initiliser les elements à afficher dans la vue comme le
	 * tableau des le lancement de l'appliocation.
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		remplirSelectHeureDebut();
		remplirSelectHeureFin();

		sre.getServletContext().setAttribute("listHeureDebut", listHeureDebut);
		sre.getServletContext().setAttribute("listHeureFin", listHeureFin);
		System.out.println("je suis dans mon requestIntializer...");

		this.jourHoraires = jourHoraireDAO.afficherTableJourHoraire();
		ecrireDansTableau();

		sre.getServletContext().setAttribute("emploiDuTemps", emploiDuTemps);
		sre.getServletContext().setAttribute("jourHoraires", jourHoraires);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Cette methode recoit la valeur du bouton reinitialiser le tableau et
	 *      traite la demande
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String btnReinit = request.getParameter("reinit");
		// reinitialisation du tableau
		if (btnReinit.equalsIgnoreCase("Reinitialiser")) {
			jourHoraireDAO.effacerTouteLesMatiereDuTableau();
			jourHoraires = jourHoraireDAO.afficherTableJourHoraire();
			ecrireDansTableau();

		}
		request.setAttribute("emploiDuTemps", emploiDuTemps);

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Cette classe traite de l'ajout d'une matiere dans le tableau, et verifie
	 *      les informations du formulaire
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperation des champs du formulaire
		String selectJours = request.getParameter("jours");
		String heureDebut = request.getParameter("heureDebut");
		String heureFin = request.getParameter("heureFin");
		String matiere = request.getParameter("matiere");

		// Gestion des messages d'erreurs
		String messageErreur = "";
		String messageErreurException = "";
		String messageErreurChampsHeureDebut = "";
		String messageErreurChampsHeureFin = "";

		System.out.println("heselectJours: " + selectJours);
		System.out.println("heure de debut: " + heureDebut);
		System.out.println("heure de fin: " + heureFin);
		System.out.println("matiere: " + matiere);

		// Verification si les champs du formulaire sont vide
		if (selectJours.isBlank() || heureDebut.isBlank() || heureFin.isBlank() || matiere.isBlank()) {
			messageErreur = "Ce champ est requis";
		} else if (Integer.parseInt(heureDebut) >= Integer.parseInt(heureFin)) {
			messageErreurChampsHeureDebut = "L'heure de debut doit etre anterieure a l'heure de fin";
			messageErreurChampsHeureFin = "L'heure de fin doit etre posterieure a l'heure de debut";
		}

		else {

			try {
				for (int i = Integer.parseInt(heureDebut); i < Integer.parseInt(heureFin); i++) {
					Jour jour = new Jour();
					jour.setJourId(Integer.parseInt(selectJours));

					Horaire horaire = new Horaire();
					horaire.setHoraireId(i);

					JourHoraire jourHoraire = new JourHoraire(jour, horaire, matiere.toUpperCase());
					jourHoraireDAO.ajouterMatiere(jourHoraire);
				}

			} catch (MatiereAlreadyExistsException e) {
				messageErreurException = "Ce créneau horaire n'est pas libre!";
			}

		}
		jourHoraires = jourHoraireDAO.afficherTableJourHoraire();
		ecrireDansTableau();

		request.setAttribute("heureFin", heureFin);
		request.setAttribute("heureDebut", heureDebut);
		request.setAttribute("selectJours", selectJours);
		request.setAttribute("matiere", matiere);

		request.setAttribute("emploiDuTemps", emploiDuTemps);
		request.setAttribute("jourHoraires", jourHoraires);

		request.setAttribute("messageErreur", messageErreur);
		request.setAttribute("messageErreurException", messageErreurException);
		request.setAttribute("messageErreurChampsHeureDebut", messageErreurChampsHeureDebut);
		request.setAttribute("messageErreurChampsHeureFin", messageErreurChampsHeureFin);

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * Methode de remplissage du select heure du début de la vue
	 */
	private void remplirSelectHeureDebut() {
		listHeureDebut = new ArrayList<>();
		listHeureDebut.add("8:30");
		for (int i = 9; i < 19; i++) {
			for (int j = 0; j < 2; j++) {
				String heure = i + ":" + tabDemiHeure[j];
				if (i < 10) {
					heure = "0" + heure;
				}
				if (heure.equalsIgnoreCase("18:30"))
					break;
				listHeureDebut.add(heure);
			}
		}
	}

	/**
	 * Methode de remplissage du select heure de fin de la vue
	 */
	private void remplirSelectHeureFin() {
		listHeureFin = new ArrayList<String>();
		for (int i = 9; i < 19; i++) {
			for (int j = 0; j < 2; j++) {
				String heure = i + ":" + tabDemiHeure[j];
				if (i < 10) {
					heure = "0" + heure;
				}
				listHeureFin.add(heure);
			}
		}
	}

	/**
	 * Methode permettant ecire dans le tableau de la vue, les elements de la BDD
	 */
	private void ecrireDansTableau() {

		for (int i = 1; i < this.emploiDuTemps.length; i++) {
			for (int j = 1; j < this.emploiDuTemps[i].length; j++) {
				for (JourHoraire jourHoraire : this.jourHoraires) {
					if (jourHoraire.getHoraire().getHoraireId() == i) {
						emploiDuTemps[i][0] = jourHoraire.getHoraire().getIntervalle();
					}
					if (jourHoraire.getJour().getJourId() == j) {
						emploiDuTemps[0][j] = jourHoraire.getJour().getNom();
					}
					if (jourHoraire != null && jourHoraire.getHoraire().getHoraireId() == i
							&& jourHoraire.getJour().getJourId() == j) {
						emploiDuTemps[i][j] = jourHoraire.getMatiere();

					}
				}
			}
		}
	}
}
