package fr.diginamic.hello.controleurs.controlleradvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.diginamic.hello.exceptions.MessageException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({ MessageException.class })
	public ResponseEntity<String> traiterErreurs(MessageException e) {
		System.out.println("traitement gestionnaire exception");
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}