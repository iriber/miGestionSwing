package com.migestion.swing.controller;

import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.search.criteria.UICriteria;

/**
 * Interfaz que define métodos para listar objetos del modelo.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IControllerList {
	
	/**
	 * se listan objetos del modelo.
	 * 
	 * @return
	 * @throws ControllerException
	 */
	public UICollection list() throws ControllerException;

	/**
	 * se listan objetos del modelo filtrándolos a partir
	 * de la información del criteria.
	 * 
	 * @param criteria
	 * @return
	 * @throws ControllerException
	 */
	public UICollection list(UICriteria criteria) throws ControllerException;
	
	/**
	 * se obtiene la cantidad de objetos.
	 * (utilizado para la paginación)
	 * @return
	 * @throws ControllerException
	 */
	public int totalSize() throws ControllerException;
	
	/**
	 * se obtiene la cantidad de objetos dado el criteria.
	 * (utilizado para la paginación)
	 * @param criteria
	 * @return
	 * @throws ControllerException
	 */
	public int totalSize(UICriteria criteria) throws ControllerException;
	
}
