package com.migestion.swing.navigation.listeners;

/**
 * Interface definida para los objetos que desean ser avisados
 * cuando el link ejecuta su acci�n y se crea un objeto nuevo.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface LinkAddListener {

	/**
	 * cuando se cree un nuevo objeto el listener ser�
	 * avisado y se le dar� dicho objeto.
	 * 
	 * @param objectCreated
	 */
	public void objectCreated(Object objectCreated);
}
