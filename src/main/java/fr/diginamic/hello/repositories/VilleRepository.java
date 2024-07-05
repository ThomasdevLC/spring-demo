package fr.diginamic.hello.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entites.Ville;

public interface VilleRepository extends CrudRepository<Ville, Integer> {
	
	Optional<Ville> findByNom(String nom);


}
