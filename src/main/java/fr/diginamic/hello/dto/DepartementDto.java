package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DepartementDto {

	@NotNull(message = "Le code du département ne peut pas être null")
	@Min(value = 1, message = "Le code du département doit être au moins égal à 1")
	private int code; 

	public DepartementDto(int id, int code) {
		this.code = code;
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
