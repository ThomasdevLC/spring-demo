package fr.diginamic.hello.controleurs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class VilleControleur {

	private List<Ville> villes = new ArrayList<>();

	public VilleControleur() {
	    villes.add(new Ville(1, "Paris", 2148000));
	    villes.add(new Ville(2, "Lyon", 516092));
	    villes.add(new Ville(3, "Marseille", 861635));
	    villes.add(new Ville(4, "Nice", 342637));
	    villes.add(new Ville(5, "Toulouse", 471941));
	}

	@GetMapping("/villes")
	public List<String> extraireVilles() {
		List<String> nomVilles = new ArrayList<>();
		for (Ville ville : villes) {
			nomVilles.add(ville.toString());
		}
		return nomVilles;
	}

	@GetMapping("/villes/{id}")
	public Ville extraireUneVille(@PathVariable int id) {
	    for (Ville ville : villes) {
	        if (ville.getId() == id) {
	            return ville;
	        }
	    }
	    return null; 
	}
	
	@PostMapping("/villes")
	public ResponseEntity<String> insererVille(@RequestBody Ville nvVille) {
	    for (Ville ville : villes) {
	        if (ville.getId() == nvVille.getId()) {
	            return ResponseEntity.badRequest().body("La ville existe déjà ");
	        }
	    }
	    
	    villes.add(nvVille);
	    return ResponseEntity.ok("Ville insérée avec succès !");
	}
	
	@PutMapping("/villes/{id}")
	public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville nvVille) {
	    for (Ville ville : villes) {
	        if (ville.getId() == id) {
	            ville.setNom(nvVille.getNom());
	            ville.setPopulation(nvVille.getPopulation());
	            return ResponseEntity.ok("Ville mise à jour avec succès !");
	        }
	    }
	    return ResponseEntity.badRequest().body("Ville non trouvée");
	}
	
	@DeleteMapping("/villes/{id}")
	public ResponseEntity<String> effacerVille(@PathVariable int id) {
	    Iterator<Ville> iterator = villes.iterator();
	    while (iterator.hasNext()) {
	        Ville ville = iterator.next();
	        if (ville.getId() == id) {
	            iterator.remove(); 
	            return ResponseEntity.ok("Ville " + id + " supprimée avec succès !");
	        }
	    }
	    return ResponseEntity.badRequest().body("Ville non trouvée");
	}
}
