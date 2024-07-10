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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exceptions.MessageException;
import fr.diginamic.hello.mapper.VilleMapper;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/villes")

public class VilleControleur {

	@Autowired
	private VilleService villeService;

	@Autowired
	private VilleMapper villeMapper;

	@GetMapping
	@Operation(summary = "Récupérer toutes les villes", description = "Renvoie la liste de toutes les villes disponibles")
	public List<VilleDto> getVilles() {
		List<Ville> villes = villeService.extractVilles();
		return villes.stream().map(villeMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/id/{id}")
	@Operation(summary = "Récupérer une ville par ID", description = "Renvoie les informations d'une ville spécifiée par son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La ville a été trouvée et renvoyée", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VilleDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Aucune ville trouvée avec l'ID spécifié") })
	public ResponseEntity<VilleDto> getVilleById(@PathVariable("id") int id) {
		Optional<Ville> ville = villeService.extractVilleById(id);
		return ville.map(v -> ResponseEntity.ok(villeMapper.toDto(v))).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<String> getVillesByName(@PathVariable String nom) {
		Optional<Ville> villes = villeService.extractVilleByName(nom);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest().body("Aucune ville trouvée avec le nom : " + nom);
		} else {
			return ResponseEntity.ok("Ville" + nom + "trouvée avec succès");
		}
	}

	@PostMapping
	public ResponseEntity<String> createVille(@Valid @RequestBody Ville nvVille, BindingResult validation)
			throws MessageException {

		if (validation.hasErrors()) {
			throw new MessageException(validation.getAllErrors().get(0).getDefaultMessage());
		}

		villeService.insertVille(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès !");

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@PathVariable int id, @Valid @RequestBody Ville nvVille,
			BindingResult validation) throws MessageException {

		if (validation.hasErrors()) {
			throw new MessageException(validation.getAllErrors().get(0).getDefaultMessage());
		}

		villeService.updateVille(id, nvVille);
		return ResponseEntity.ok("Ville mise à jour avec succès !");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) throws MessageException {
		boolean isDeleted = villeService.deleteVille(id);
		if (!isDeleted) {
			throw new MessageException("Erreur: La ville avec l'ID " + id + " n'existe pas.");
		}
		return ResponseEntity.ok("Ville " + id + " supprimée avec succès !");
	}

	@GetMapping("/débutnom/{débutnom}")
	public ResponseEntity<String> getVillesByStartWith(@PathVariable String débutnom) {
		List<VilleDto> villes = villeService.findVillesStartWith(débutnom);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest().body("Aucune ville trouvée commençant par  : " + débutnom);
		} else {
			return ResponseEntity.ok("Villes commençant par : " + débutnom + "  " + villes);
		}
	}

	@GetMapping("/minpop/{minpop}")
	public ResponseEntity<String> getVillesPopOver(@PathVariable("minpop") int minPop) {
		List<Ville> villes = villeService.findVillesbyPopOver(minPop);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest()
					.body("Aucune ville trouvée dont la population est supérieure à  : " + minPop);
		} else {
			return ResponseEntity.ok("Villes dont la population est supérieur à : " + minPop + " " + villes);
		}
	}

	@GetMapping("/popentre/{min}/{max}")
	public ResponseEntity<String> findVillesbyPopOver(@PathVariable("min") int min, @PathVariable("max") int max) {
		List<Ville> villes = villeService.findVillesbyPopBetween(min, max);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest()
					.body("Aucune ville trouvée dont la population est comprise entre  : " + min + " et " + max);
		} else {
			return ResponseEntity
					.ok("Villes dont la population est comprise entre  : " + min + " et " + max + " " + villes);
		}
	}

	@GetMapping("/minpopDprt/{departementCode}/{min}")
	public ResponseEntity<List<Ville>> getVillesByPopGreaterThan(@PathVariable int departementCode,
			@PathVariable int min) {
		List<Ville> villes = villeService.findVillesByDepartementAndPopGreaterThan(departementCode, min);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest().body(villes);
		} else {
			return ResponseEntity.ok(villes);
		}
	}

	@GetMapping("/popDprtEntre/{departementCode}/{min}/{max}")
	public ResponseEntity<List<Ville>> getVillesByPopBetween(@PathVariable int departementCode, @PathVariable int min,
			@PathVariable int max) {
		List<Ville> villes = villeService.findVillesByDepartementAndPopBetween(departementCode, min, max);

		if (villes.isEmpty()) {
			return ResponseEntity.badRequest().body(villes);
		} else {
			return ResponseEntity.ok(villes);
		}
	}

	@GetMapping("/topVilles/{departementCode}/{n}")
	public ResponseEntity<List<Ville>> getTopNVillesByDepartement(@PathVariable("departementCode") int code,
			@PathVariable int n) {
		List<Ville> villes = villeService.getTopVillesByDepartement(code, n);

		if (villes.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(villes);
		}
	}
}