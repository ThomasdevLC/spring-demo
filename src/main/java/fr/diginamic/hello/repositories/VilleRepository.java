package fr.diginamic.hello.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entites.Ville;

public interface VilleRepository extends CrudRepository<Ville, Integer> {
	
	Optional<Ville> findByNom(String nom);
    List<Ville> findByNomStartingWith(String start);
    List<Ville> findByPopulationGreaterThan(int min);
    List<Ville> findByPopulationBetween(int min, int max);
    List<Ville> findByDepartementCodeAndPopulationGreaterThan(int code, int min);
    List<Ville> findByDepartementCodeAndPopulationBetween(int code, int min, int max);
	


}
