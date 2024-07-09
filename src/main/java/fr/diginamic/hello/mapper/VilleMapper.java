package fr.diginamic.hello.mapper;

import org.springframework.stereotype.Component;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entites.Ville;

@Component
public class VilleMapper {

	// Conversion de Ville en VilleDto
	public VilleDto toDto(Ville ville) {
		VilleDto dto = new VilleDto();
		dto.setNom(ville.getNom());
		dto.setPopulation(ville.getPopulation());
		return dto;
	}

	// Conversion de VilleDto en Ville
	public Ville toBean(VilleDto villeDto) {
		Ville ville = new Ville();
		ville.setNom(villeDto.getNom());
		ville.setPopulation(villeDto.getPopulation());
		return ville;
	}
}
