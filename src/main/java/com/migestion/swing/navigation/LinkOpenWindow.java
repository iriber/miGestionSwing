package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowOpen;

/**
 * Link para abrir una ventana.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class LinkOpenWindow extends Link {

	//ventana que ser� cerrada
	protected ILinkWindowOpen window;
	
	//--------------------
	//CONSTRUCTORES
	//--------------------
	
	public LinkOpenWindow(String title, String pathImage){
		this(title, pathImage, pathImage);
	}
	public LinkOpenWindow(String title, URL pathImage){
		this(title, pathImage, pathImage);
	}
	/**
	 * 
	 */
	public LinkOpenWindow(String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Open_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F10);
		//imagen por default
		setImageName(pathSmallIcon);
		//descripci�n por default
		setDescription(title);
	}

	public LinkOpenWindow(String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Open_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F10);
		//imagen por default
		setImageURL(pathSmallIcon);
		//descripci�n por default
		setDescription(title);
	}


	public LinkOpenWindow(String title){
		this(title, LinkImagesBundle.link_Open);
	}
	
	/**
	 *
	 */
	public LinkOpenWindow(){
		this(LinkLabelsBundle.link_Open, LinkImagesBundle.link_Open);
	}	

	/**
	 * 
	 */
	public LinkOpenWindow(ILinkWindowOpen window, String title, String pathImage){
		this(title, pathImage);
		this.window = window;		
	}
	
	/**
	 * 
	 */
	public LinkOpenWindow(ILinkWindowOpen window, String title){
		this(title);
		this.window = window;		
	}
	public LinkOpenWindow(ILinkWindowOpen window, String title, URL pathSmallIcon){
		this(title);
		this.window = window;
		setImageURL(pathSmallIcon);
	}
	/**
	 * 
	 * @param window
	 */
	public LinkOpenWindow(ILinkWindowOpen window){	
		this(LinkLabelsBundle.link_Open, LinkImagesBundle.link_Open);
		this.window = window;		
	}
	
	/**
	 * se abre la ventana.
	 */
	protected void doMyExecute() throws LinkException {
		if(getWindow()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Close_windowNotDefined);
		getWindow().open();
	}

	/**
	 * se abre la ventana.
	 */
	protected void doMyExecute(ILinkWindowOpen window) throws LinkException {
		this.window = window;
		doExecute();
	}
	
	public ILinkWindowOpen getWindow(){
		return window;
	}
	
}
