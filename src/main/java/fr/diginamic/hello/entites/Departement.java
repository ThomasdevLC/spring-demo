package fr.diginamic.hello.entites;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Classe représentant un Département.
 * Le Département doit avoir un code d'au moins 1.
 */
@Entity
public class Departement {
	 /**
     * Identifiant unique du Département.
     * Il est généré automatiquement par la base de données lors de l'insertion.
     */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 /**
     * Code du département.
     * Doit être au moins égale à 1.
     */
	@NotNull(message = "Le code du département ne peut pas être null")
	@Min(value = 1, message = "Le code du département doit être au moins égal à 1")
	private int code;
	

	 /**
    * Constructeur paramétré pour créer une instance de  Ville}.
    * 
    * @param code : le code du département 
    */

	public Departement(int code) {
		super(); 
		this.code = code;
	}

	public Departement() {
		super();
	}

	@OneToMany(mappedBy = "departement")
	private List<Ville> villes;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Departement)) {
			return false;
		}
		Departement other = (Departement) obj;
		return code == other.code && id == other.id;
	}

	@Override
	public String toString() {
		return "code = " + code;
	}

}
