package com.migestion.swing.navigation;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowAdd;
import com.migestion.swing.navigation.interfaces.ILinkWindowOpen;
import com.migestion.swing.navigation.listeners.LinkAddListener;

/**
 * Link abrir un dialog en general.
 * 
 * @author Bernardo Iribarne
 * @since 07/11/2113
 */
public abstract class LinkOpenDialog extends Link {

	
	public LinkOpenDialog(String title, URL pathSmallIcon, URL pathLargeIcon, String keyStroke){
		super ( title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
	}
	
	public LinkOpenDialog(String title, URL pathSmallIcon, String keyStroke){
		super ( title, pathSmallIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
	}
	
	public LinkOpenDialog(String title, String pathSmallIcon, String pathLargeIcon, String keyStroke){
		super ( title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageName(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
	}
	
	
	public LinkOpenDialog(String title, URL pathSmallIcon){
		super ( title, pathSmallIcon);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.migestion.swing.navigation.Link#doMyExecute()
	 */
	protected void doMyExecute() throws LinkException{

		//abrimos el dialog
		
		getDialog().open();		
		
	}

	protected abstract ILinkWindowOpen getDialog() ;

}
