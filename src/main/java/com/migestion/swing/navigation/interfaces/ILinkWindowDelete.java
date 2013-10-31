package com.migestion.swing.navigation.interfaces;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkDeleteObject.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowDelete {

	/**
	 * retorna true si el usuario aceptó la operación
	 * 
	 * @return
	 */
	public boolean isAcepted();
	
	/**
	 * se abre la ventana mostrando el objeto 
	 * a eliminar.
	 */
	public void open(Object objectToDelete);
}
