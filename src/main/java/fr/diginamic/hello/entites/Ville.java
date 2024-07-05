package fr.diginamic.hello.entites;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 2) @NotNull	 
	private String nom;
	
	@Min(value = 1) @NotNull
	private int population;

	public Ville( String nom, int population) {
		super();
		this.nom = nom;
		this.population = population;
	}

	
	
	public Ville() {
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return " [id :" + " " + id + " - " + nom + " - " + population + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(nom, population);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ville)) {
			return false;
		}
		Ville other = (Ville) obj;
		return Objects.equals(nom, other.nom) && population == other.population;
	}



}
