package com.migestion.swing.custom;

/**
 * Para obtener informaci�n sobre el objecto encontrado.
 * 
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public interface IObjectFoundInspector {
	/**
	 * retorna el c�digo del objecto.
	 * @param object
	 * @return
	 */
	public String getCode(Object object);
	
	/**
	 * retorna la descripci�n del objeto.
	 * @param object
	 * @return
	 */
	public String getDescription(Object object);

}
