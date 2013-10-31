package com.migestion.swing.navigation.interfaces;

/**
 * Interfaz que deberán implementar las ventanas a utilizar
 * con un LinkAddRelatedObject.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkWindowAddRelated {

	
	/**
	 * retorna el objeto creado.
	 * @return
	 */
	public Object getObjectCreated();
	
	/**
	 * retorna true si el usuario aceptó la operación
	 * 
	 * @return
	 */
	public boolean isAcepted();
	
	/**
	 * se abre la ventana en la pantalla
	 * con el objeto relacionado
	 */
	public void open(Object relatedObject);	
}
