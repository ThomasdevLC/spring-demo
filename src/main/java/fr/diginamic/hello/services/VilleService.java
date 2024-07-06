package fr.diginamic.hello.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VilleService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private DepartementRepository departementRepository;

	public List<Ville> extractVilles() {
		return (List<Ville>) villeRepository.findAll();
	}

	public Optional<Ville> extractVilleById(int idVille) {
		return villeRepository.findById(idVille);
	}

	public Optional<Ville> extractVilleByName(String nom) {
		return villeRepository.findByNom(nom);
	}

	public void insertVille(Ville ville) {
		Departement departement = ville.getDepartement();

		// Rechercher le département par son code
		Optional<Departement> existingDepartement = departementRepository.findByCode(departement.getCode());

		if (existingDepartement.isPresent()) {
			// Utilisez le département existant
			ville.setDepartement(existingDepartement.get());
		} else {
			// Sauvegardez le nouveau département
			departementRepository.save(departement);
		}

		// Maintenant, vous pouvez sauvegarder la ville
		villeRepository.save(ville);
	}

	public void updateVille(int idVille, Ville villeModifiee) {
		Optional<Ville> ville = villeRepository.findById(idVille);
		if (ville.isPresent()) {
			Ville existingVille = ville.get();
			existingVille.setNom(villeModifiee.getNom());
			existingVille.setPopulation(villeModifiee.getPopulation());
			villeRepository.save(existingVille);
		}
	}

	public void deleteVille(int idVille) {
		villeRepository.deleteById(idVille);
	}

	public List<Ville> findVillesStartWith(String start) {
		return villeRepository.findByNomStartingWith(start);
	}

	public List<Ville> findVillesbyPopOver(int min) {
		return villeRepository.findByPopulationGreaterThan(min);
	}

	public List<Ville> findVillesbyPopBetween(int min, int max) {
		return villeRepository.findByPopulationBetween(min, max);
	}

	public List<Ville> findVillesByDepartementAndPopGreaterThan(int code, int min) {
		return villeRepository.findByDepartementCodeAndPopulationGreaterThan(code, min);
	}

	public List<Ville> findVillesByDepartementAndPopBetween(int code, int min, int max) {
		return villeRepository.findByDepartementCodeAndPopulationBetween(code, min, max);
	}
}
