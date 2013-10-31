package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowClose;

/**
 * Link para salir de una ventana.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkCloseWindow extends Link {

	//ventana que ser� cerrada
	protected ILinkWindowClose window;
	
	//--------------------
	//CONSTRUCTORES
	//--------------------	
	/**
	 * 
	 */
	public LinkCloseWindow(String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		setKeyStroke(esc);
		//setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Close_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_ESCAPE);
		//imagen por default
		setImageName(pathSmallIcon);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Close);
	}
	
	public LinkCloseWindow(String title, URL pathSmallIcon){
		this(title, pathSmallIcon, pathSmallIcon );
	}
	public LinkCloseWindow(String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		setKeyStroke(esc);
		//setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Close_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_ESCAPE);
		//imagen por default
		setImageURL(pathSmallIcon);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Close);
	}

	public LinkCloseWindow(String title, String pathImage){
		this(title, pathImage, pathImage);
	}
	
	/**
	 *
	 */
	public LinkCloseWindow(){
		this(LinkLabelsBundle.link_Close, LinkImagesBundle.link_Close);
	}	

	/**
	 * 
	 * @param window
	 */
	public LinkCloseWindow(ILinkWindowClose window){	
		this(LinkLabelsBundle.link_Close, LinkImagesBundle.link_Close);
		this.window = window;		
	}
	
	/**
	 * se cierra la ventana.
	 */
	protected void doMyExecute() throws LinkException {
		if(getWindow()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Close_windowNotDefined);
		getWindow().close();
	}

	/**
	 * se cierra la ventana.
	 */
	protected void doMyExecute(ILinkWindowClose window) throws LinkException {
		this.window = window;
		doExecute();
	}
	
	protected ILinkWindowClose getWindow(){
		return window;
	}
}
