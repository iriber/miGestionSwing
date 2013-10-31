package com.migestion.swing.custom;

/**
 * Interfaz para recuperar un objeto dado su c�digo.
 *
 * Se utiliza con el componente JFindObjectPanel.
 * 
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public interface IFinderObjectByCode {
	/**
	 * retorna un objeto dado su c�digo.
	 * @param code
	 * @return object
	 */
	public Object getObject(String code);
	
}
