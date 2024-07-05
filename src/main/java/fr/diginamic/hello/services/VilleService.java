package fr.diginamic.hello.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class VilleService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private VilleRepository villeRepository;

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
}
