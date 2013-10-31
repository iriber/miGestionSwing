package com.migestion.swing.navigation.listeners;

/**
 * Interface definida para los objetos que desean ser avisados
 * cuando el link ejecuta su acci�n y modifica el objeto.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface LinkUpdateListener {

	/**
	 * cuando se modifique el objeto el listener ser�
	 * avisado y se le dar� dicho objeto.
	 * 
	 * @param objectCreated
	 */
	public void objectUpdated(Object objectUpdated);
}
