package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DepartementDto {

	private int id;

	@NotNull(message = "Le code du département ne peut pas être null")
	@Min(value = 1, message = "Le code du département doit être au moins égal à 1")
	private int code;

	public DepartementDto(int id, int code) {
		this.id = id;
		this.code = code;
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

	public DepartementDto() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
