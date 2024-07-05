package fr.diginamic.hello.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity<>("Aucune ville trouvée avec cet id : " , HttpStatus.NOT_FOUND);
        } else {
    		return ResponseEntity.ok("Ville trouvée");
        }
	}

	@GetMapping("/nom/{nom}")
    public ResponseEntity<String> getVillesByName(@PathVariable String nom) {
		Optional<Ville> villes = villeService.extractVilleByName(nom);

        if (villes.isEmpty()) {
            return new ResponseEntity<>("Aucune ville trouvée avec le nom : " + nom, HttpStatus.NOT_FOUND);
        } else {
    		return ResponseEntity.ok("Ville"  + nom  + "trouvée avec succès");
        }
	}

	@PostMapping
	public ResponseEntity<String> createVille(@Valid @RequestBody Ville nvVille, BindingResult validation) {

		if (validation.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur de validation: ");
		}

		villeService.insertVille(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès !");
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@Valid @PathVariable int id, @RequestBody Ville nvVille,
			BindingResult validation) {

		if (validation.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur de validation: ");
		}

		villeService.updateVille(id, nvVille);
		return ResponseEntity.ok("Ville mise à jour avec succès !");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) {
		villeService.deleteVille(id);
		return ResponseEntity.ok("Ville " + id + " supprimée avec succès !");
	}
}
