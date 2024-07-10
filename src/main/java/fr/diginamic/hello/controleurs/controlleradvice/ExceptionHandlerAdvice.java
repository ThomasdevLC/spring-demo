package fr.diginamic.hello.controleurs.controlleradvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.diginamic.hello.exceptions.MessageException;

/**
 * Classe de gestion globale des exceptions qui gère les {@link MessageException}
 * et renvoie une réponse d'erreur.
 */

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	/**
     * Gère les {@link MessageException} et renvoie une réponse de mauvaise requête avec
     * le message d'erreur fourni par l'exception.
     *
     * @param e l'instance {@link MessageException} lancée pendant l'exécution de l'application.
     * @return un ResponseEntity avec un statut de mauvaise requête et le message d'erreur
     *         provenant de l'exception comme corps de réponse.
     */
	

	@ExceptionHandler({ MessageException.class })
	public ResponseEntity<String> traiterErreurs(MessageException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}