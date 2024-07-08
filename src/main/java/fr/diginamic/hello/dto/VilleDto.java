package fr.diginamic.hello.dto;

public class VilleDto {
	private String nom;
	private int population;



	public VilleDto( String nom, int population) {
		this.nom = nom;
		this.population = population;
	}

	public VilleDto() {
	};

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
}
