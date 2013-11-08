package com.migestion.swing.navigation.interfaces;

import com.migestion.swing.view.frames.JFrameContainer;

/**
 * Interfaz para las ventanas que se abren
 * con un objeto
 * 
 * @author Bernardo Iribarne
 * @since 07/11/2013
 */
public interface ILinkWindowObjectOpen {

	
	/**
	 * se abre la ventana en la pantalla mostrando
	 * el objeto a modificar.
	 */
	public void open(Object objectToUpdate);	
	
	/**
	 * se agrega en un frame container.
	 */
	public void addToJFrameContainer(JFrameContainer container);
}
