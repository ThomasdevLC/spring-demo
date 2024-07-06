package fr.diginamic.hello.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import fr.diginamic.hello.entites.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {
    Optional<Departement> findByCode(int code);
}
