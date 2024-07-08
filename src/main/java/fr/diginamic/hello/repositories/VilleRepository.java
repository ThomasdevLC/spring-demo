package fr.diginamic.hello.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.diginamic.hello.entites.Ville;


/**
 * Repository de gestion des opérations CRUD et des requêtes spécifiques pour rechercher des villes en fonction de divers critères 
 * sur les entités {@link Ville}.
 * 
 */
public interface VilleRepository extends CrudRepository<Ville, Integer> {
	
	 /**
     * Recherche une ville par son nom.
     * 
     * @param nom le nom de la ville recherchée
     * @return  la ville trouvée, ou vide si aucune ville ne correspond
     */
	Optional<Ville> findByNom(String nom);
	
	   /**
     * Recherche les villes dont le nom commence par une chaîne de caractères donnée.
     * 
     * @param start la chaîne de caractères par laquelle le nom des villes doit commencer
     * @return une liste de villes dont le nom commence par la chaîne donnée
     */
    List<Ville> findByNomStartingWith(String start);
    
    /**
     * Recherche les villes dont la population est supérieure à une valeur donnée.
     * 
     * @param min la valeur minimale de la population
     * @return une liste de villes dont la population est supérieure à la valeur spécifiée
     */
    List<Ville> findByPopulationGreaterThan(int min);
    
    /**
     * Recherche les villes dont la population est comprise entre deux valeurs données.
     * 
     * @param min la valeur minimale de la population
     * @param max la valeur maximale de la population
     * @return une liste de villes dont la population est comprise entre les deux valeurs spécifiées
     */
    List<Ville> findByPopulationBetween(int min, int max);
    
    /**
     * Recherche les villes d'un département donné dont la population est supérieure à une valeur donnée.
     * 
     * @param code le code du département
     * @param min la valeur minimale de la population
     * @return une liste de villes du département avec une population supérieure à la valeur spécifiée
     */
    List<Ville> findByDepartementCodeAndPopulationGreaterThan(int code, int min);
    /**
     * Recherche les villes d'un département donné dont la population est comprise entre deux valeurs données.
     * 
     * @param code le code du département
     * @param min la valeur minimale de la population
     * @param max la valeur maximale de la population
     * @return une liste de villes du département avec une population comprise entre les deux valeurs spécifiées
     */
    List<Ville> findByDepartementCodeAndPopulationBetween(int code, int min, int max);
    /**
     * Recherche les villes d'un département donné, triées par population décroissante.
     * 
     * 
     * @param code le code du département
     * @param pageable: nombre de résultats et ordre
     * @return une liste de villes triées par population décroissante pour le département spécifié
     */
    @Query("SELECT v FROM Ville v WHERE v.departement.code = :code ORDER BY v.population DESC")
    List<Ville> findTopNVillesByDepartement(@Param("code") int code, Pageable pageable);
}
