package fr.diginamic.hello.mapper;

import org.springframework.stereotype.Component;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;

@Component
public class VilleMapper {

    public VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();
        dto.setId(ville.getId());
        dto.setNom(ville.getNom());
        dto.setPopulation(ville.getPopulation());
        dto.setCodeDepartement(toDepartementDto(ville.getDepartement())); 
        return dto;
    }

    public Ville toBean(VilleDto villeDto) {
        Ville ville = new Ville();
        ville.setId(villeDto.getId());
        ville.setNom(villeDto.getNom());
        ville.setPopulation(villeDto.getPopulation());
        ville.setDepartement(toDepartement(villeDto.getCodeDepartement())); 
        return ville;
    }

    public DepartementDto toDepartementDto(Departement departement) {
        return new DepartementDto(departement.getId(), departement.getCode());
    }

    public Departement toDepartement(DepartementDto departementDto) {
        return new Departement(departementDto.getCode());
    }
}

