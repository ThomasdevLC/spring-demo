package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VilleDto {

	/**
	 * Nom de la ville. Doit contenir au moins 2 caractères.
	 */
	@Size(min = 2, message = "Le nom de la ville doit avoir au moins 2 caractères.")
	private String nom;
	/**
	 * Population de la ville. Doit être au moins égale à 1.
	 */
	@NotNull(message = "La population ne peut pas être nulle.")
	@Min(value = 1, message = "La population doit être au moins égale à 1.")
	private int population;

	public VilleDto(String nom, int population, String codeDepartement) {
		this.nom = nom;
		this.population = population;
	}

	public VilleDto() {
		super();
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "VilleDto [nom=" + nom + ", population=" + population + "]";
	}



}
