package com.migestion.swing.controller;

import com.migestion.swing.controller.exception.ControllerException;


/**
 * Interfaz que define métodos para visualizar objetos del modelo.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IControllerView {
	
	/**
	 * se obtiene el objeto del modelo.
	 * @param object
	 * @throws ControllerException
	 */
	public Object getObject(Object object) throws ControllerException;

}
