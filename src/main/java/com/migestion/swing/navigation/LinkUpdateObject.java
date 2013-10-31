package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowUpdate;
import com.migestion.swing.navigation.listeners.LinkUpdateListener;
import com.migestion.swing.view.frames.ListCollectionListener;

/**
 * Link para modificar un elemento. Llama a la ventana
 * para modificar un elemento.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkUpdateObject extends Link implements ListCollectionListener{


	//dialogo para modificar el objeto
	protected ILinkWindowUpdate dialog;
	
	//objeto a ser modificado
	private Object objectToUpdate;
	
	//objetos listeners que ser�n informados cuando
	//se modifique el objeto
	private Vector listeners;
	
	
	//----------------
	//CONTRUCTORES
	//----------------
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para modificar la informaci�n del objeto.
	 */
	public LinkUpdateObject(ILinkWindowUpdate dialog){
		this(dialog, LinkLabelsBundle.link_Update, LinkImagesBundle.link_Update);
		this.setEnabled(false);
	}
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para modificar la informaci�n del objeto.
	 * 
	 * @param dialog
	 * @param title
	 */
	public LinkUpdateObject(ILinkWindowUpdate dialog, String title){
		this(dialog, title, LinkImagesBundle.link_Update);
		this.setEnabled(false);
	}

	/**
	 * se construye con el di�logo que ser� invocado
	 * para modificar la informaci�n del objeto.
	 * 
	 * @param dialog
	 * @param title
	 * @param pathImage
	 */
	public LinkUpdateObject(ILinkWindowUpdate dialog, String title, String pathImage){
		this (dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	
	public LinkUpdateObject(ILinkWindowUpdate dialog, String title, URL pathImage){
		this (dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	
	public LinkUpdateObject(ILinkWindowUpdate dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Update_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F7);
		//imagen 
		setImageName(pathLargeIcon);
		//descripci�n 
		setDescription(title);
		
		//this.setEnabled(false);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		
		setEnabled(false);
		
	}
	
	public LinkUpdateObject(ILinkWindowUpdate dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Update_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F7);
		//imagen 
		setImageURL(pathSmallIcon);
		//descripci�n 
		setDescription(title);
		
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		
		this.setEnabled(false);
	}
	
	/**
	 * retorna el objeto modificado.
	 * @return
	 */
	public Object getObjectUpdated() {
		return getDialog().getObjectUpdated();
	}

	/**
	 * se setea el objeto a ser modificado.
	 * @return
	 */
	public void setObjectToUpdate(Object objectToUpdate) {
		this.objectToUpdate = objectToUpdate;
		this.setEnabled(this.objectToUpdate!= null);
	}
	
	/**
	 * retorna si el objeto fue modificado o si se cancel�
	 * la acci�n. 
	 * @return
	 */
	public boolean isUpdated(){
		return getDialog().isAcepted();
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{
		//verificamos que se haya inicializado el objeto
		//a modificar
		if(this.objectToUpdate==null)
			throw new LinkException(ExceptionMessagesBundle.link_Update_objectNotDefined);
		
		//se llama al di�logo para modificar el objeto.
		getDialog().open(this.objectToUpdate);		
		//si fue aceptada la operaci�n, avisamos a los listeners
		//que el objeto fue modificado.
		if(getDialog().isAcepted())
			alertListeners();
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	public void doExecute(Object objectToUpdate) throws LinkException{
		//seteamos el objeto a modificar
		setObjectToUpdate(objectToUpdate);
		//se llama al di�logo para modificar el objeto.
		doExecute();		
	}

	/**
	 * se agrega un listener para avisarle y darle
	 * el objeto modificado.
	 * 
	 * @param listener
	 */
	public void addListener(LinkUpdateListener listener){
		this.listeners.add(listener);
	}
	
	/*
	 * se avisa a los listener que el objeto
	 * fue creado.  
	 */
	private void alertListeners(){
		Iterator iter = listeners.iterator();
		while (iter.hasNext()) {
			//le avisamos a cada listener
			LinkUpdateListener element = (LinkUpdateListener) iter.next();
			element.objectUpdated(getObjectUpdated());			
		}
	}

	/**
	 * el link es listener de un listado, cuando cambia el objeto
	 * seleccionado de la lista, se setea como el objeto a modificar. 
	 * @param selectedObject
	 */
	public void valueSelectedChange(Object selectedObject) {
		setObjectToUpdate(selectedObject);
	}
	
	protected ILinkWindowUpdate getDialog(){
		return dialog;
	}
}
