package com.migestion.swing.navigation.interfaces;



/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkFindObjects.
 * 
 * @author Bernardo Iribarne
 */
public interface ILinkWindowFindObject {

	
	/**
	 * retorna el objeto encontrado
	 * @return
	 */
	public Object getObjectFinded();
	
	
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