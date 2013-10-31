package com.migestion.swing.navigation.interfaces;

import com.migestion.swing.view.frames.JFrameContainer;


/**
 * Interfaz que deberán implementar las ventanas que se 
 * desea usarlas con el LinkOpenWindow.
 * 
 * @author Bernardo Iribarne
 *
 */

public interface ILinkWindowOpen {

	
	/**
	 * abre la ventana mostrándola en pantalla.
	 */
	public void open();
	
	/**
	 * se agrega en un frame container.
	 */
	public void addToJFrameContainer(JFrameContainer container);
	
	
	
}
