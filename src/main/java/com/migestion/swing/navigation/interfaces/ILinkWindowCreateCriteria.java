package com.migestion.swing.navigation.interfaces;

import com.migestion.swing.search.criteria.UICriteria;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkCreateCriteria.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowCreateCriteria {

	
	/**
	 * retorna el criteria creado
	 * @return
	 */
	public UICriteria getUICriteria();
	
	
	/**
	 * retorna true si el usuario aceptó la operación
	 * 
	 * @return
	 */
	public boolean isAcepted();
	
	/**
	 * se abre la ventana en la pantalla.
	 */
	public void open();
}
