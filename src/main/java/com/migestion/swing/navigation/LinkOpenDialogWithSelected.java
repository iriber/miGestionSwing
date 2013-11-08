package com.migestion.swing.navigation;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowAdd;
import com.migestion.swing.navigation.interfaces.ILinkWindowObjectOpen;
import com.migestion.swing.navigation.interfaces.ILinkWindowOpen;
import com.migestion.swing.navigation.listeners.LinkAddListener;
import com.migestion.swing.view.frames.ListCollectionListener;

/**
 * Link abrir un dialog pasándole los objectos seleccionados en un listado.
 * 
 * @author Bernardo Iribarne
 * @since 07/11/2113
 */
public abstract class LinkOpenDialogWithSelected extends Link implements ListCollectionListener{

	protected Object object;
	
	public LinkOpenDialogWithSelected(String title, URL pathSmallIcon, URL pathLargeIcon, String keyStroke){
		super ( title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		
		setEnabled(false);
	}
	
	public LinkOpenDialogWithSelected(String title, URL pathSmallIcon, String keyStroke){
		super ( title, pathSmallIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		
		setEnabled(false);
	}
	
	public LinkOpenDialogWithSelected(String title, String pathSmallIcon, String pathLargeIcon, String keyStroke){
		super ( title, pathSmallIcon, pathLargeIcon);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(keyStroke));
		//imagen del link
		setImageName(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		
		setEnabled(false);
	}
	
	public LinkOpenDialogWithSelected(String title, String pathSmallIcon){
		super ( title, pathSmallIcon);
		//imagen del link
		setImageName(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		
		setEnabled(false);
	}
	
	public LinkOpenDialogWithSelected(String title, URL pathSmallIcon){
		super ( title, pathSmallIcon);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		
		setEnabled(false);
	}
	
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
		this.setEnabled(this.object!= null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.swing.navigation.Link#doMyExecute()
	 */
	protected void doMyExecute() throws LinkException{

		//verificamos que se haya inicializado el objeto
		//a modificar
		if(this.getObject()==null)
			throw new LinkException(getObjectNotSelectedMessage());
		//abrimos el dialog
		
		getDialog().open( this.getObject() );		
		
	}

	protected String getObjectNotSelectedMessage(){
		return ExceptionMessagesBundle.link_Update_objectNotDefined;
	}
	
	protected abstract ILinkWindowObjectOpen getDialog() ;
	
	/**
	 * el link es listener de un listado, cuando cambia el objeto
	 * seleccionado de la lista, se setea como el objeto a modificar. 
	 * @param selectedObject
	 */
	public void valueSelectedChange(Object selectedObject) {
		this.setObject(selectedObject);
	}

}
