package com.migestion.swing.navigation.listeners;

/**
 * Interface definida para los objetos que desean ser avisados
 * cuando el link ejecuta su acci�n y se elimina el objeto.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface LinkDeleteListener {

	/**
	 * cuando se elimina el objeto el listener ser�
	 * avisado.
	 * 
	 */
	public void objectDeleted(Object objectDeleted);
}
