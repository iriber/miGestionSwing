package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;

/**
 * Link para salir del sistema.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkSystemExit extends LinkCloseWindow {

	
	
	/**
	 * se cierra el sistema.
	 */
	protected void doMyExecute() throws LinkException {	
		System.exit(0);
	}

	
	public LinkSystemExit(){
		super(LinkLabelsBundle.link_Exit, LinkImagesBundle.link_Exit);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Exit_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_Q);
		//imagen por default
		//setImageName(LinkImagesBundle.link_Exit);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Exit);		
	}	
	
}
