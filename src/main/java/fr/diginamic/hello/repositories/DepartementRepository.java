package fr.diginamic.hello.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import fr.diginamic.hello.entites.Departement;


/**
 * Repository de gestion des opérations CRUD pour rechercher des départements en fonction de critères 
 * sur les entités {@link Departement}.
 * 
 */
public interface DepartementRepository extends CrudRepository<Departement, Integer> {
	
	 /**
     * Recherche une département par son code.
     * 
     * @param code le code du département recherchée
     * @return  le département trouvée, ou vide si aucun département ne correspond
     */
    Optional<Departement> findByCode(int code);
}
