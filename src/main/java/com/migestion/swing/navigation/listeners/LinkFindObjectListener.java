package com.migestion.swing.navigation.listeners;


/**
 * Interface definida para los objetos que desean ser avisados
 * cuando el link ejecuta su acci�n y se encuentra un objeto.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public interface LinkFindObjectListener {

	/**
	 * cuando se encuentre el objeto el listener ser�
	 * avisado y se le dar� dicho objeto.
	 * 
	 * @param 
	 */
	public void objectFound(Object objectFinded);
	
}
