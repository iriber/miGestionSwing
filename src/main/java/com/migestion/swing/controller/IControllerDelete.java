package com.migestion.swing.controller;

import com.migestion.swing.controller.exception.ControllerException;

/**
 * Interfaz que define métodos para eliminar objetos del modelo.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IControllerDelete {
	
	/**
	 * se elimina el objeto del modelo
	 * @param object
	 * @throws ControllerException
	 */
	public void deleteObject(Object object) throws ControllerException;

}
