package com.migestion.swing.navigation.interfaces;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkViewObject.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowView {

	/**
	 * se abre la ventana en la pantalla mostrando
	 * el objeto.
	 */
	public void open(Object objectToView);	
}
