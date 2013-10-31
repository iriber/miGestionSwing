package com.migestion.swing.controller;

import com.migestion.swing.controller.exception.ControllerException;

/**
 * Interfaz que define métodos para modificar objetos del modelo.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IControllerUpdate {
	
	/**
	 * se modificar el objeto en el modelo
	 * @param object
	 * @throws ControllerException
	 */
	public void updateObject(Object object) throws ControllerException;

}
