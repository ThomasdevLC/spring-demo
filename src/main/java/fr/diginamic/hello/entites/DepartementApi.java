package fr.diginamic.hello.entites;


public class DepartementApi {

    private String nom;
    private String code;
    private String codeRegion;
    
    
	public DepartementApi(String nom, String code, String codeRegion) {
		super();
		this.nom = nom;
		this.code = code;
		this.codeRegion = codeRegion;
	}


	public DepartementApi() {
		super();
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @return the codeRegion
	 */
	public String getCodeRegion() {
		return codeRegion;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @param codeRegion the codeRegion to set
	 */
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}


	@Override
	public String toString() {
		return "DepartementApi [nom=" + nom + ", code=" + code + ", codeRegion=" + codeRegion + "]";
	}

  
    
    
    
}
