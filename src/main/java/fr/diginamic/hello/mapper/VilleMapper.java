package fr.diginamic.hello.mapper;


import org.springframework.stereotype.Component;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entites.Ville;

@Component
public class VilleMapper {

    // Conversion de Ville en VilleDto
    public VilleDto toDto(Ville ville) {
        if (ville == null) {
            return null;
        }
        return new VilleDto(ville.getNom(), ville.getPopulation());
    }

    // Conversion de VilleDto en Ville
    public Ville toBean(VilleDto villeDto) {
        if (villeDto == null) {
            return null;
        }
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());
        ville.setPopulation(villeDto.getPopulation());
        return ville;
    }
}
