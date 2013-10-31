package com.migestion.swing.navigation.interfaces;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkUpdateObject.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowUpdate {

	
	/**
	 * retorna el objeto modificado.
	 * @return
	 */
	public Object getObjectUpdated();
	
	/**
	 * retorna true si el usuario aceptó la operación
	 * 
	 * @return
	 */
	public boolean isAcepted();
	
	/**
	 * se abre la ventana en la pantalla mostrando
	 * el objeto a modificar.
	 */
	public void open(Object objectToUpdate);	
}
