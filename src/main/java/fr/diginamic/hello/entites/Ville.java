package fr.diginamic.hello.entites;

public class Ville {

	private int id;
	private String nom;
	private int population;

	public Ville(int id, String nom, int population) {
		super();
		this.id = id;
		this.nom = nom;
		this.population = population;
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
		return " [id :" + " "+ id + " - " + nom + " - " + population + "]";
	}

}
