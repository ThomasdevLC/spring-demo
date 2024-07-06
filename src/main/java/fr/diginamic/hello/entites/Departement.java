package fr.diginamic.hello.entites;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int code;
	
	public Departement( int code) {
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
		return "code = " + code ;
	}
	
	

}
