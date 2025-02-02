package fr.diginamic.hello.entites;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



/**
 * Classe représentant une Ville.
 * La ville doit avoir un nom d'au moins 2 caractères et une population d'au moins 1.

 */

@Entity
public class Ville {
	 /**
     * Identifiant unique de la ville.
     * Il est généré automatiquement par la base de données lors de l'insertion.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
     * Nom de la ville.
     * Doit contenir au moins 2 caractères.
     */
	@Size(min = 2, message = "Le nom de la ville doit avoir au moins 2 caractères.")
	private String nom;
	  /**
     * Population de la ville.
     * Doit être au moins égale à 1.
     */
	@NotNull(message = "La population ne peut pas être nulle.")
	@Min(value = 1, message = "La population doit être au moins égale à 1.")
	private int population;

	@ManyToOne
	@JoinColumn(name = "id_departement")
	private Departement departement;
	
	
	 /**
     * Constructeur paramétré pour créer une instance de  Ville}.
     * 
     * @param nom le nom de la ville
     * @param population la population de la ville
     * @param departement le département auquel la ville appartient
     */

	public Ville(String nom, int population, Departement departement) {
		super();
		this.nom = nom;
		this.population = population;
		this.departement = departement;
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

	/**
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Override
	public String toString() {
		return " [" + nom + ", population=" + population + ", departement = " + departement + "]";
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
