package fr.diginamic.hello.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import fr.diginamic.hello.entites.Ville;

@Service
@Transactional
public class VilleServiceSql {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Ville> extractVilles() {
		String databaseQuery = "SELECT v FROM Ville v";
		TypedQuery<Ville> query = entityManager.createQuery(databaseQuery, Ville.class);
		return query.getResultList();
	}

	public Ville extractVilleById(int idVille) {
		return entityManager.find(Ville.class, idVille);
	}

	public List<Ville> extractVilleByName(String nom) {
		String databaseQuery = "SELECT v FROM Ville v WHERE v.nom = :nom";
		TypedQuery<Ville> query = entityManager.createQuery(databaseQuery, Ville.class);
		query.setParameter("nom", nom);
		return query.getResultList();
	}

	public void insertVille(Ville ville) {
		List<Ville> existingVilles = extractVilleByName(ville.getNom());

		if (existingVilles.isEmpty()) {
			entityManager.persist(ville);
		}
	}

	public void updateVille(int idVille, Ville villeModifiee) {
		Ville ville = entityManager.find(Ville.class, idVille);
		if (ville != null) {
			ville.setNom(villeModifiee.getNom());
			ville.setPopulation(villeModifiee.getPopulation());
		}
	}

	public void deleteVille(int idVille) {
		Ville ville = entityManager.find(Ville.class, idVille);
		if (ville != null) {
			entityManager.remove(ville);
		}
	}
}