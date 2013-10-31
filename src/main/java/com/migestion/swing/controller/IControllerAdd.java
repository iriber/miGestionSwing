package com.migestion.swing.controller;

import com.migestion.swing.controller.exception.ControllerException;


/**
 * Interfaz que define métodos para agregar nuevos objetos al modelo.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IControllerAdd {
	
	/**
	 * se agrega el objeto al modelo
	 * @param object
	 * @throws ControllerException
	 */
	public void addObject(Object object) throws ControllerException;

}
