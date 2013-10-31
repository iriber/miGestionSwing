package com.migestion.swing.navigation.interfaces;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkAddObject.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowAdd {


	/**
	 * @return objeto creado
	 */
	public Object getObjectCreated();
	
	
	/**
	 * @return true si el usuario aceptó la operación
	 */
	public boolean isAcepted();
	
	/**
	 * se abre la ventana en la pantalla.
	 */
	public void open();
}
