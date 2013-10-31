package com.migestion.swing.navigation.listeners;

import com.migestion.swing.search.criteria.UICriteria;

/**
 * Interface definida para los objetos que desean ser avisados
 * cuando el link ejecuta su acción y se crea un criteria.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface LinkCreateCriteriaListener {

	/**
	 * cuando se cree un criteria el listener será
	 * avisado y se le dará dicho objeto.
	 * 
	 * @param criteriaCreated
	 */
	public void criteriaCreated(UICriteria criteriaCreated);
}
