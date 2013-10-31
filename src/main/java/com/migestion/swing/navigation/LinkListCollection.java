package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.KeyStroke;

import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowList;

/**
 * Link para visualizar una colecci�n de elementos. Llama a la ventana
 * para visualizar una colecci�n.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkListCollection extends Link {


	//ventana para visualizar la colecci�n
	protected ILinkWindowList dialog;
	
	//controlador del cual se obtiene la colecci�n.
	protected IControllerList controller;
	
	//------------------
	//CONTRUCTORES
	//------------------
	/**
	 * se construye con el di�logo que ser� invocado
	 * para cargar la informaci�n del nuevo objeto.
	 * si el di�logo es nulo, el link se deshabilitar�.
	 */
	public LinkListCollection(ILinkWindowList dialog){
		this(dialog, LinkLabelsBundle.link_List, LinkImagesBundle.link_List);
	}
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para cargar la informaci�n del nuevo objeto.
	 * si el di�logo es nulo, el link se deshabilitar�.
	 * 
	 * @param dialog
	 * @param title
	 * @param pathImage
	 */
	public LinkListCollection(ILinkWindowList dialog, String title, String pathImage){
		this( dialog, title, pathImage, pathImage);
	}
	
	public LinkListCollection(ILinkWindowList dialog, String title, URL pathImage){
		this( dialog, title, pathImage, pathImage);
	}
	
	public LinkListCollection(ILinkWindowList dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//key stroke por default.
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_List_KeyStroke));
		//key event por default.
		//setKeyEvent(KeyEvent.VK_F10);
		//imagen del link.
		setImageName(pathSmallIcon);
		//descripci�n del link.
		setDescription(title);
		//lo deshabilitamos si el frame es nulo.
//		if(dialog==null)
//			setEnabled(false);
	}	
	
	public LinkListCollection(ILinkWindowList dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//key stroke por default.
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_List_KeyStroke));
		//key event por default.
		//setKeyEvent(KeyEvent.VK_F10);
		//imagen del link.
		setImageURL(pathSmallIcon);
		//descripci�n del link.
		setDescription(title);
		//lo deshabilitamos si el frame es nulo.
//		if(dialog==null)
//			setEnabled(false);
	}	
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para cargar la informaci�n del nuevo objeto.
	 * si el di�logo es nulo, el link se deshabilitar�.
	 * 
	 * @param dialog
	 * @param title
	 */
	public LinkListCollection(ILinkWindowList dialog, String title){
		this(dialog, title, LinkImagesBundle.link_List);
	}	

	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{
		//se llama al frame para visualizar la colecci�n.
		getDialog().open();	
		
	}


	public ILinkWindowList getDialog() {
		return dialog;
	}

	
}
