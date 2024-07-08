package fr.diginamic.hello.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/villes")

public class VilleControleur {

	@Autowired
	private VilleService villeService;

	@GetMapping
	public List<Ville> getVilles() {
		return villeService.extractVilles();
	}

	@GetMapping("/id/{id}")
    public ResponseEntity<String> getVilleById(@PathVariable("id") int id) {
		
		Optional<Ville> villes = villeService.extractVilleById(id);
		if (villes.isEmpty()) {
			return ResponseEntity.badRequest().body("Aucune ville trouvée avec l'id: " + id);
        } else {
    		return ResponseEntity.ok("Ville avec l'id " + id +  " trouvée");
        }
	}

	@GetMapping("/nom/{nom}")
    public ResponseEntity<String> getVillesByName(@PathVariable String nom) {
		Optional<Ville> villes = villeService.extractVilleByName(nom);

        if (villes.isEmpty()) {
            return  ResponseEntity.badRequest().body("Aucune ville trouvée avec le nom : " + nom);
        } else {
    		return ResponseEntity.ok("Ville"  + nom  + "trouvée avec succès");
        }
	}

	@PostMapping
	public ResponseEntity<String> createVille(@Valid @RequestBody Ville nvVille, BindingResult validation) {

		if (validation.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur de validation ");
		}

		villeService.insertVille(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès !");
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@Valid @PathVariable int id, @RequestBody Ville nvVille,
			BindingResult validation) {

		if (validation.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur de validation ");
		}

		villeService.updateVille(id, nvVille);
		return ResponseEntity.ok("Ville mise à jour avec succès !");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) {
		villeService.deleteVille(id);
		return ResponseEntity.ok("Ville " + id + " supprimée avec succès !");
	}
	
	@GetMapping("/débutnom/{débutnom}")
    public ResponseEntity<String> getVillesByStartWith(@PathVariable String débutnom) {
		List<Ville> villes = villeService.findVillesStartWith(débutnom);

        if (villes.isEmpty()) {
            return  ResponseEntity.badRequest().body("Aucune ville trouvée commençant par  : " + débutnom);
        } else {
    		return ResponseEntity.ok("Villes commençant par : " + débutnom + "  " + villes  );
        }
	}
	
	@GetMapping("/minpop/{minpop}")
    public ResponseEntity<String> getVillesPopOver(@PathVariable("minpop") int minPop) {
		List<Ville> villes = villeService.findVillesbyPopOver(minPop);

        if (villes.isEmpty()) {
            return  ResponseEntity.badRequest().body("Aucune ville trouvée dont la population est supérieure à  : " + minPop);
        } else {
    		return ResponseEntity.ok("Villes dont la population est supérieur à : " + minPop + " "  + villes  );
        }
	}
	
    @GetMapping("/popentre/{min}/{max}") 
    public ResponseEntity<String> findVillesbyPopOver(@PathVariable("min") int min, @PathVariable("max") int max) {
		List<Ville> villes = villeService.findVillesbyPopBetween(min, max);

        if (villes.isEmpty()) {
            return  ResponseEntity.badRequest().body("Aucune ville trouvée dont la population est comprise entre  : " + min +" et " + max);
        } else {
    		return ResponseEntity.ok("Villes dont la population est comprise entre  : " + min + " et " + max + " " + villes  );
        }
	}
    
    @GetMapping("/minpopDprt/{departementCode}/{min}")
    public ResponseEntity<List<Ville>> getVillesByPopGreaterThan(@PathVariable int departementCode, @PathVariable int min) {
        List<Ville> villes = villeService.findVillesByDepartementAndPopGreaterThan(departementCode, min);

        if (villes.isEmpty()) {
            return ResponseEntity.badRequest().body(villes);
        } else {
            return ResponseEntity.ok(villes);
        }
    }

    @GetMapping("/popDprtEntre/{departementCode}/{min}/{max}")
    public ResponseEntity<List<Ville>> getVillesByPopBetween(@PathVariable int departementCode, @PathVariable int min, @PathVariable int max) {
        List<Ville> villes = villeService.findVillesByDepartementAndPopBetween(departementCode, min, max);

        if (villes.isEmpty()) {
            return ResponseEntity.badRequest().body(villes);
        } else {
            return ResponseEntity.ok(villes);
        }
    }
    
    @GetMapping("/topVilles/{departementCode}/{n}")
    public ResponseEntity<List<Ville>> getTopNVillesByDepartement(@PathVariable("departementCode") int code, @PathVariable int n) {
        List<Ville> villes = villeService.getTopVillesByDepartement(code, n);
        
        if (villes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(villes); 
        }
    }
}