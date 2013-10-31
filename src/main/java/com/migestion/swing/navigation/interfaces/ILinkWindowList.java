package com.migestion.swing.navigation.interfaces;

import com.migestion.swing.view.frames.JFrameContainer;



/**
 * Interfaz que deberán implementar las ventanas que muestran
 * una colección de objetos y se desea usarlas con el LinkListCollection.
 * 
 * @author Bernardo Iribarne
 *
 */

public interface ILinkWindowList {

	
	/**
	 * abre la ventana mostrándola en pantalla.
	 */
	public void open();
	
	/**
	 * se agrega en un frame container.
	 */
	public void addToJFrameContainer(JFrameContainer container);
	
	
	
}
