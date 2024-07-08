package fr.diginamic.hello.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service de gestion des opérations métier pour les entités {@link Ville}.
 * Fournit des méthodes pour gérer les villes :  leur extraction, insertion,
 * mise à jour et suppression, ainsi que des recherches basées selon des critères .
 * 
 * VilleService interagit avec {@link VilleRepository} et {@link DepartementRepository}.
 */

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private DepartementRepository departementRepository;
	
    /**
    * Extrait toutes les villes de la base de données.
    * 
    * @return une liste de toutes les villes
    */

	public List<Ville> extractVilles() {
		return (List<Ville>) villeRepository.findAll();
	}

	  /**
     * Extrait une ville par son identifiant.
     * 
     * @param idVille l'identifiant de la ville à extraire
     * @return  la ville trouvée, ou vide si aucune ville ne correspond
     */
	public Optional<Ville> extractVilleById(int idVille) {
		return villeRepository.findById(idVille);
	}

	  /**
     * Extrait une ville par son nom.
     * 
     * @param nom le nom de la ville à extraire
     * @return  la ville trouvée, ou vide si aucune ville ne correspond
     */
	public Optional<Ville> extractVilleByName(String nom) {
		return villeRepository.findByNom(nom);
	}

	
	  /**
     * Insère une nouvelle ville dans la base de données.
     * Si le département associé à la ville n'existe pas déjà, il est ajouté.
     * 
     * @param ville :  la ville à insérer
     */
	public void insertVille(Ville ville) {
		
		Departement departement = ville.getDepartement();
		Optional<Departement> existingDepartement = departementRepository.findByCode(departement.getCode());
		
		if (existingDepartement.isPresent()) {
			ville.setDepartement(existingDepartement.get());
		} else {
			departementRepository.save(departement);
		}

		villeRepository.save(ville);
	}

	
	 /**
     * Met à jour une ville existante avec les nouvelles informations fournies.
     * 
     * @param idVille l'identifiant de la ville à mettre à jour
     * @param villeModifiee les nouvelles informations pour la ville
     */
	public void updateVille(int idVille, Ville villeModifiee) {
		Optional<Ville> ville = villeRepository.findById(idVille);
		if (ville.isPresent()) {
			Ville existingVille = ville.get();
			existingVille.setNom(villeModifiee.getNom());
			existingVille.setPopulation(villeModifiee.getPopulation());
			villeRepository.save(existingVille);
		}
	}

	  /**
     * Supprime une ville de la base de données selon son identifiant.
     * 
     * @param idVille l'identifiant de la ville à supprimer
     */
	public void deleteVille(int idVille) {
		villeRepository.deleteById(idVille);
	}

	
    /**
     * Recherche les villes dont le nom commence par une chaîne de caractères donnée.
     * 
     * @param start la chaîne de caractères par laquelle le nom des villes doit commencer
     * @return une liste de villes dont le nom commence par la chaîne donnée
     */

	public List<Ville> findVillesStartWith(String start) {
		return villeRepository.findByNomStartingWith(start);
	}

	
	  /**
     * Recherche les villes dont la population est supérieure à une valeur donnée.
     * 
     * @param min la valeur minimale de la population
     * @return une liste de villes dont la population est supérieure à la valeur spécifiée
     */
	public List<Ville> findVillesbyPopOver(int min) {
		return villeRepository.findByPopulationGreaterThan(min);
	}

	  /**
     * Recherche les villes dont la population est comprise entre deux valeurs données.
     * 
     * @param min la valeur minimale de la population
     * @param max la valeur maximale de la population
     * @return une liste de villes dont la population est comprise entre les deux valeurs spécifiées
     */
	
	public List<Ville> findVillesbyPopBetween(int min, int max) {
		return villeRepository.findByPopulationBetween(min, max);
	}

	  /**
     * Recherche les villes d'un département donné dont la population est supérieure à une valeur donnée.
     * 
     * @param code le code du département
     * @param min la valeur minimale de la population
     * @return une liste de villes du département avec une population supérieure à la valeur spécifiée
     */
	public List<Ville> findVillesByDepartementAndPopGreaterThan(int code, int min) {
		return villeRepository.findByDepartementCodeAndPopulationGreaterThan(code, min);
	}

	  /**
     * Recherche les villes d'un département donné dont la population est comprise entre deux valeurs données.
     * 
     * @param code le code du département
     * @param min la valeur minimale de la population
     * @param max la valeur maximale de la population
     * @return une liste de villes du département avec une population comprise entre les deux valeurs spécifiées
     */
	public List<Ville> findVillesByDepartementAndPopBetween(int code, int min, int max) {
		return villeRepository.findByDepartementCodeAndPopulationBetween(code, min, max);
	}
	
	 /**
     * Recherche les villes d'un département donné, triées par population décroissante.
     * 
     * @param code le code du département
     * @param n le nombre maximum de villes à retourner
     * @return une liste des villes les plus peuplées d'un département, triées par population décroissante
     */
	 public List<Ville> getTopVillesByDepartement(int code, int n) {
	        Pageable pageable = PageRequest.of(0, n);
	        return villeRepository.findTopNVillesByDepartement(code, pageable);
	    }
}
