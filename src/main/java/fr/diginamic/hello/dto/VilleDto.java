package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VilleDto {
    private int id;

    @Size(min = 2, message = "Le nom de la ville doit avoir au moins 2 caractères.")
    private String nom;

    @NotNull(message = "La population ne peut pas être nulle.")
    @Min(value = 1, message = "La population doit être au moins égale à 1.")
    private int population;

    private DepartementDto codeDepartement;

    public VilleDto(int id, String nom, int population, DepartementDto codeDepartement) {
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.codeDepartement = codeDepartement;
    }

    public VilleDto() {
        super();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public DepartementDto getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(DepartementDto codeDepartement) {
        this.codeDepartement = codeDepartement;
    }
}

